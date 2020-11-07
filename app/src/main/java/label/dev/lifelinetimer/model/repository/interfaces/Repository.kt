package label.dev.lifelinetimer.model.repository.interfaces

import androidx.lifecycle.LiveData
import label.dev.lifelinetimer.model.models.apimodels.NewsModel
import label.dev.lifelinetimer.model.models.dbmodels.notes.NoteModel
import label.dev.lifelinetimer.model.models.dbmodels.tasks.SubTaskModel
import label.dev.lifelinetimer.model.models.dbmodels.tasks.TaskInfoModel
import label.dev.lifelinetimer.model.models.dbmodels.tasks.TaskModel
import java.time.LocalDateTime

interface Repository {

    //Api
    suspend fun getNews(): LiveData<NewsModel>

    //NotesDao
    val readAllNotes: LiveData<List<NoteModel>>
    suspend fun deleteNote(note: NoteModel)
    suspend fun updateNote(note: NoteModel)
    suspend fun saveNote(note: NoteModel)

    //TaskDao
    val readAllTasks: LiveData<List<TaskModel>>
    suspend fun addTask(taskInfo: TaskInfoModel, subtasks: List<SubTaskModel>?)
    suspend fun deleteTask(taskInfo: TaskInfoModel, subtasks: List<SubTaskModel>?)
    suspend fun updateTask(taskInfo: TaskInfoModel, subtasks: List<SubTaskModel>?)

    //Time
    fun getCurrentTime(): String
    fun formatTimeToString(time: LocalDateTime): String
    fun formatStringToTime(time: String): LocalDateTime

    //Sorters
    fun noteMapSorterByDate(list: LiveData<List<NoteModel>>): LiveData<List<NoteModel>>
}