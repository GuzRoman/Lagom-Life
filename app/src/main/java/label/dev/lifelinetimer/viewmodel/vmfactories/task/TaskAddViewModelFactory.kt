package label.dev.lifelinetimer.viewmodel.vmfactories.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import label.dev.lifelinetimer.model.repository.interfaces.Repository
import label.dev.lifelinetimer.viewmodel.tasksvm.TaskAddViewModel

class TaskAddViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskAddViewModel(repository) as T
    }
}