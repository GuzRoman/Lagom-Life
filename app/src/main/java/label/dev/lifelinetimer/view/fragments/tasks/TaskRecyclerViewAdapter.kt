package label.dev.lifelinetimer.view.fragments.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item.view.*
import label.dev.lifelinetimer.R
import label.dev.lifelinetimer.model.models.dbmodels.tasks.TaskModel

class TaskRecyclerViewAdapter : RecyclerView.Adapter<TaskRecyclerViewAdapter.TaskViewHolder>() {

    private var tasks = mutableListOf<TaskModel>()

    class TaskViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var title: TextView = item.findViewById(R.id.taskTitle)
        var subTitle: TextView = item.findViewById(R.id.taskSubtitle)
        var notDoneSubtasks: TextView = item.findViewById(R.id.taskSubtaskNotDoneNubmer)
        var daysToEnd: TextView = item.findViewById(R.id.taskEndTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.title.text = task.taskInfoModel!!.title
        holder.subTitle.text = task.taskInfoModel!!.subTitle
        holder.daysToEnd.text = task.taskInfoModel!!.endDate
        holder.notDoneSubtasks.text =
            task.taskInfoModel!!.notDoneSubtasks.toString() + " / " + task.taskInfoModel!!.subtaskCount.toString()

        holder.itemView.notesRawLayout.setOnClickListener {
           val action = TasksFragmentDirections.actionTasksFragmentToSubtasksFragment(task)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(setNotes: List<TaskModel>) {
        tasks.addAll(setNotes)
        notifyDataSetChanged()
    }

}