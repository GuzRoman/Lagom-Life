package label.dev.lifelinetimer.view.fragments.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task_item.view.*
import label.dev.lifelinetimer.R
import label.dev.lifelinetimer.model.models.dbmodels.tasks.TaskModel
import label.dev.lifelinetimer.view.fragments.notes.NotesFragmentDirections
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TaskRecyclerViewAdapter : RecyclerView.Adapter<TaskRecyclerViewAdapter.TaskViewHolder>() {

    private var tasks = mutableListOf<TaskModel>()
    private val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")

    class TaskViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var title: TextView = item.findViewById(R.id.taskTitle)
        var subTitle: TextView = item.findViewById(R.id.taskSubtitle)
//        var notDoneSubtasks: TextView = item.findViewById(R.id.taskSubtaskNotDoneNubmer)
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
        holder.daysToEnd.text = daysToEnd(task)
//        holder.notDoneSubtasks.text =
//            task.taskInfoModel!!.notDoneSubtasks.toString() + " / " + task.taskInfoModel!!.subtaskCount.toString()

        holder.itemView.taskRawLayout.setOnClickListener {
            val action = TasksFragmentDirections.actionTasksFragmentToSubtasksFragment(task)
            holder.itemView.findNavController().navigate(action)
        }
    }

    private fun daysToEnd(taskModel: TaskModel): String{
        val startDate = formatStringToTime(LocalDateTime.now().format(formatter))
        val endDate = formatStringToTime(taskModel.taskInfoModel!!.endDate)

        val days = Duration.between(startDate,endDate).toDays()

        return days.toString()
    }

    private fun formatStringToTime(time: String): LocalDateTime {
        val formattedStringToTime = LocalDateTime.parse(time, formatter)
        return formattedStringToTime
    }

    fun setData(setNotes: List<TaskModel>) {
        tasks.addAll(setNotes)
        notifyDataSetChanged()
    }

}