package label.dev.lifelinetimer.view.fragments.tasks

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.taskadd_fragment.*
import kotlinx.android.synthetic.main.taskadd_fragment.view.*
import label.dev.lifelinetimer.R
import label.dev.lifelinetimer.model.models.dbmodels.tasks.TaskInfoModel
import label.dev.lifelinetimer.model.models.dbmodels.tasks.TaskModel
import label.dev.lifelinetimer.view.MainActivity
import label.dev.lifelinetimer.viewmodel.tasksvm.TaskAddViewModel
import label.dev.lifelinetimer.viewmodel.tasksvm.TasksViewModel
import label.dev.lifelinetimer.viewmodel.vmfactories.task.TaskAddViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import java.util.*

class TaskAddFragment : Fragment(), DatePickerDialog.OnDateSetListener , KodeinAware{
    override val kodein: Kodein by closestKodein()

    private val taskAddViewModelFactory: TaskAddViewModelFactory by instance<TaskAddViewModelFactory>()

    private lateinit var taskAddViewModel: TaskAddViewModel

    var day = 0
    var month = 0
    var year = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.taskadd_fragment, container, false)

        (requireActivity() as MainActivity).bottomNavigationView.visibility = View.GONE

        view.taskDialogViewSetTimeBTN.setOnClickListener {
            getDateTimeCalendar()
            DatePickerDialog(requireContext(), this, year, month, day).show()
        }

        taskAddViewModel = ViewModelProvider(this, taskAddViewModelFactory).get(TaskAddViewModel::class.java)

        view.taskDialogViewSaveBTN.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase(){
        val taskTitle = taskdialogSetTitle.text.toString()
        val taskSubtitle = taskdialogSetSubtitle.text.toString()
        val taskEndDate = taskdialogTime.text.toString()

        if (taskTitle.isEmpty() || taskEndDate.isEmpty() || taskSubtitle.isEmpty()){
            Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
        } else{
            val task = TaskModel(TaskInfoModel(0, taskTitle, taskSubtitle, taskEndDate, getCurrentDate(), 0, 0))
            saveTask(task)
            Toast.makeText(requireContext(), "Заметка сохранена", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_taskRedactorFragment_to_tasksFragment)
        }
    }

    private fun getCurrentDate() = taskAddViewModel.getCurrentTime()

    private fun saveTask(task: TaskModel){
        taskAddViewModel.saveTask(task)
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {

        savedDay = day
        savedMonth = month + 1
        savedYear = year

        taskdialogTime.text = "$savedDay.$savedMonth.$savedYear"
    }

    private fun getDateTimeCalendar(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        (requireActivity() as MainActivity).bottomNavigationView.visibility = View.VISIBLE
    }
}