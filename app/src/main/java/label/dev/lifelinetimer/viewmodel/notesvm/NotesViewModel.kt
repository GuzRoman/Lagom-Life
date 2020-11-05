package label.dev.lifelinetimer.viewmodel.notesvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import label.dev.lifelinetimer.model.api.NetService
import label.dev.lifelinetimer.model.db.DaoImpl
import label.dev.lifelinetimer.model.models.dbmodels.notes.NoteModel
import label.dev.lifelinetimer.model.repository.RepositoryImpl

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private val repositoryImpl: RepositoryImpl
    var notesList: LiveData<List<NoteModel>>

    init {
        val taskDao = DaoImpl.getDatabaseInstance(application).taskDao()
        val noteDao = DaoImpl.getDatabaseInstance(application).notesDao()
        repositoryImpl = RepositoryImpl(taskDao, noteDao, NetService())
        notesList = repositoryImpl.readAllNotes
    }

}