package label.dev.lifelinetimer.viewmodel.tasksvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import label.dev.lifelinetimer.model.api.NetService
import label.dev.lifelinetimer.model.db.DaoImpl
import label.dev.lifelinetimer.model.models.dbmodels.tasks.TaskModel
import label.dev.lifelinetimer.model.repository.RepositoryImpl

class TasksViewModel(application: Application) : AndroidViewModel(application) {

    private val repositoryImpl: RepositoryImpl
    var tasksList: LiveData<List<TaskModel>>

    init {
        val taskDao = DaoImpl.getDatabaseInstance(application).taskDao()
        val noteDao = DaoImpl.getDatabaseInstance(application).notesDao()
        repositoryImpl = RepositoryImpl(taskDao, noteDao, NetService())
        tasksList = repositoryImpl.readAllTasks
    }
}