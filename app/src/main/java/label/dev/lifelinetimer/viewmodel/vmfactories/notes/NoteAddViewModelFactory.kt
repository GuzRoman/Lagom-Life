package label.dev.lifelinetimer.viewmodel.vmfactories.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import label.dev.lifelinetimer.model.repository.interfaces.Repository
import label.dev.lifelinetimer.viewmodel.notesvm.NoteAddViewModel

class NoteAddViewModelFactory (private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoteAddViewModel(repository) as T
    }
}