package label.dev.lifelinetimer.viewmodel.notesvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import label.dev.lifelinetimer.model.models.dbmodels.notes.NoteModel
import label.dev.lifelinetimer.model.repository.interfaces.Repository

class NotesViewModel(private val repository: Repository) : ViewModel() {

    var notesList: LiveData<List<NoteModel>> = repository.readAllNotes

}