package label.dev.lifelinetimer.view.fragments.tasks

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.notes_fragment.*
import kotlinx.android.synthetic.main.tasks_fragment.*
import kotlinx.android.synthetic.main.tasks_fragment.view.*
import label.dev.lifelinetimer.R
import label.dev.lifelinetimer.viewmodel.tasksvm.TasksViewModel
import label.dev.lifelinetimer.viewmodel.vmfactories.task.TasksViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class TasksFragment : Fragment(), KodeinAware {
    override val kodein: Kodein by closestKodein()

    private val tasksViewModelFactory: TasksViewModelFactory by instance<TasksViewModelFactory>()

    private lateinit var taskViewModel: TasksViewModel
    private lateinit var taskRecyclerViewAdapter: TaskRecyclerViewAdapter
    private lateinit var taskRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.tasks_fragment, container, false)

        taskRecyclerViewAdapter = TaskRecyclerViewAdapter()
        taskViewModel = ViewModelProvider(this, tasksViewModelFactory).get(TasksViewModel::class.java)
        taskRecyclerView = view.tasksRecyclerView
        taskRecyclerView.adapter = taskRecyclerViewAdapter

        setData()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskAddBTN.setOnClickListener {
            findNavController().navigate(R.id.action_tasksFragment_to_taskRedactorFragment)
        }
    }

    private fun setData(){
        taskViewModel.tasksList.observe(viewLifecycleOwner, Observer { list ->
            taskRecyclerViewAdapter.setData(list)
        })
    }


}