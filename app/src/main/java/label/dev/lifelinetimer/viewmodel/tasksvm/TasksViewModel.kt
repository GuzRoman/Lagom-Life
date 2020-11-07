package label.dev.lifelinetimer.viewmodel.tasksvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import label.dev.lifelinetimer.model.models.dbmodels.tasks.TaskModel
import label.dev.lifelinetimer.model.repository.interfaces.Repository

class TasksViewModel(private val repository: Repository): ViewModel() {


    var tasksList: LiveData<List<TaskModel>>

    init {
        tasksList = repository.readAllTasks
    }
}