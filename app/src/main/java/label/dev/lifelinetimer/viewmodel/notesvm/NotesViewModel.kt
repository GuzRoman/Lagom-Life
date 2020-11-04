package label.dev.lifelinetimer.viewmodel.notesvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import label.dev.lifelinetimer.model.api.NetService
import label.dev.lifelinetimer.model.db.DaoImpl
import label.dev.lifelinetimer.model.models.dbmodels.NoteModel
import label.dev.lifelinetimer.model.repository.RepositoryImpl

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private val repositoryImpl: RepositoryImpl
    var notesList: LiveData<List<NoteModel>>

    init {
        val dao = DaoImpl.getDatabaseInstance(application).notesDao()
        repositoryImpl = RepositoryImpl(dao, NetService())
        notesList = repositoryImpl.readAllNotes
    }

}