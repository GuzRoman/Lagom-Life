package label.dev.lifelinetimer.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import label.dev.lifelinetimer.model.db.NotesDao
import label.dev.lifelinetimer.model.models.dbmodels.NoteModel
import label.dev.lifelinetimer.model.repository.interfaces.NotesDaoRepository
import label.dev.lifelinetimer.model.repository.interfaces.MappersRepository
import label.dev.lifelinetimer.model.repository.interfaces.TimeRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RepositoryImpl(private val notesDao: NotesDao) : NotesDaoRepository, TimeRepository,
    MappersRepository {

    //NoteDaoRepository

    val readAllNotes = noteMapSorterByDate(notesDao.getAllNotes())

    override suspend fun deleteNote(note: NoteModel) {
        notesDao.deleteNote(note)
    }

    override suspend fun updateNote(note: NoteModel) {
        notesDao.updateNote(note)
    }

    override suspend fun saveNote(note: NoteModel) {
        notesDao.saveNote(note)
    }

    //TimeRepository

    private val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")

    override fun getCurrentTime(): String {
        val current = LocalDateTime.now()
        val formattedTime = current.format(formatter).toString()
        return formattedTime
    }

    override fun formatTimeToString(time: LocalDateTime): String {
        val formattedTimeToString = time.format(formatter).toString()
        return formattedTimeToString
    }

    override fun formatStringToTime(time: String): LocalDateTime {
        val formattedStringToTime = LocalDateTime.parse(time, formatter)
        return formattedStringToTime
    }

    //SortersRepository

    override fun noteMapSorterByDate(list: LiveData<List<NoteModel>>): LiveData<List<NoteModel>> {
        val sortByDate = list.map { notes ->
            notes.sortedByDescending { it.noteLastupdate }
        }

        return sortByDate
    }
}