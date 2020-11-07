package label.dev.lifelinetimer.viewmodel.tasksvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import label.dev.lifelinetimer.model.models.dbmodels.tasks.TaskModel
import label.dev.lifelinetimer.model.repository.interfaces.Repository

class TaskAddViewModel(private val repository: Repository) : ViewModel() {

    fun getCurrentTime() = repository.getCurrentTime()

    fun saveTask(task: TaskModel){
        viewModelScope.launch(Dispatchers.IO) {
        repository.addTask(task.taskInfoModel!!, task.tasks)
        }
    }

}