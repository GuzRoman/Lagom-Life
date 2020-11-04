package label.dev.lifelinetimer.model.db

import androidx.lifecycle.LiveData
import androidx.room.*
import label.dev.lifelinetimer.model.models.dbmodels.NoteModel


@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveNote(note: NoteModel)

    @Query("SELECT * FROM ${NoteModel.NOTE_TABLENAME}")
    fun getAllNotes(): LiveData<List<NoteModel>>

    @Delete
    suspend fun deleteNote(note: NoteModel)

    @Update
    suspend fun updateNote(note: NoteModel)

}