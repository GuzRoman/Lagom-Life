package label.dev.lifelinetimer.model.db

import androidx.lifecycle.LiveData
import androidx.room.*
import label.dev.lifelinetimer.model.models.dbmodels.notes.NoteModel
import label.dev.lifelinetimer.model.models.dbmodels.tasks.SubTaskModel
import label.dev.lifelinetimer.model.models.dbmodels.tasks.TaskInfoModel
import label.dev.lifelinetimer.model.models.dbmodels.tasks.TaskModel


@Dao
interface AppDao {

    //Notes

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveNote(note: NoteModel)

    @Query("SELECT * FROM ${NoteModel.NOTE_TABLENAME}")
    fun getAllNotes(): LiveData<List<NoteModel>>

    @Delete
    suspend fun deleteNote(note: NoteModel)

    @Update
    suspend fun updateNote(note: NoteModel)

}