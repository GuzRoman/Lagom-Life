package label.dev.lifelinetimer.viewmodel.notesvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import label.dev.lifelinetimer.R
import label.dev.lifelinetimer.model.db.DaoImpl
import label.dev.lifelinetimer.model.models.dbmodels.ColorMarks
import label.dev.lifelinetimer.model.models.dbmodels.NoteModel
import label.dev.lifelinetimer.model.repository.RepositoryImpl

class NoteRedactorViewModel(application: Application) : AndroidViewModel(application) {

    private val repositoryImpl: RepositoryImpl

    init {
        val noteDao = DaoImpl.getDatabaseInstance(application).notesDao()
        repositoryImpl = RepositoryImpl(noteDao)
    }

    fun updateTime(): String{
        return repositoryImpl.getCurrentTime()
    }

    fun updateNote(note: NoteModel){
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.updateNote(note)
        }
    }

    fun deleteNote(note: NoteModel){
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.deleteNote(note)
        }
    }

    fun findBackGround(color:String)= when (color) {
        ColorMarks.WHITE.name -> R.drawable.cm_white
        ColorMarks.BLUE.name -> R.drawable.cm_blue
        ColorMarks.GREEN.name -> R.drawable.cm_green
        ColorMarks.PURPLE.name -> R.drawable.cm_purple
        ColorMarks.RED.name -> R.drawable.cm_red
        ColorMarks.YELLOW.name -> R.drawable.cm_yellow
        else -> R.drawable.cm_white
    }
}