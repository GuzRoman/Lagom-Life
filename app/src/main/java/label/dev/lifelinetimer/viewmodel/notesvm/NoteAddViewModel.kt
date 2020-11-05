package label.dev.lifelinetimer.viewmodel.notesvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import label.dev.lifelinetimer.R
import label.dev.lifelinetimer.model.api.NetService
import label.dev.lifelinetimer.model.db.DaoImpl
import label.dev.lifelinetimer.model.models.dbmodels.notes.ColorMarks
import label.dev.lifelinetimer.model.models.dbmodels.notes.NoteModel
import label.dev.lifelinetimer.model.repository.RepositoryImpl

class NoteAddViewModel(application: Application) : AndroidViewModel(application) {

    fun saveNote(note: NoteModel){
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.saveNote(note)
        }
    }

    fun getCurrentTime(): String{
        return repositoryImpl.getCurrentTime()
    }

    private val repositoryImpl: RepositoryImpl

    init {
        val taskDao = DaoImpl.getDatabaseInstance(application).taskDao()
        val noteDao = DaoImpl.getDatabaseInstance(application).notesDao()
        repositoryImpl = RepositoryImpl(taskDao,noteDao, NetService())
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