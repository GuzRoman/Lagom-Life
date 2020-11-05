package label.dev.lifelinetimer.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import label.dev.lifelinetimer.model.api.NetService
import label.dev.lifelinetimer.model.db.AppDao
import label.dev.lifelinetimer.model.db.TaskDao
import label.dev.lifelinetimer.model.models.apimodels.NewsModel
import label.dev.lifelinetimer.model.models.dbmodels.notes.NoteModel
import label.dev.lifelinetimer.model.models.dbmodels.tasks.SubTaskModel
import label.dev.lifelinetimer.model.models.dbmodels.tasks.TaskInfoModel
import label.dev.lifelinetimer.model.models.dbmodels.tasks.TaskModel
import label.dev.lifelinetimer.model.repository.interfaces.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RepositoryImpl(private val taskDao: TaskDao, private val appDao: AppDao, private val netService: NetService) : NotesDaoRepository, TasksDaoRepository, TimeRepository,
    MappersRepository, ApiRepository {

    //TaskDaoRepository

    val readAllTasks = taskDao.readAllTasks()

    override suspend fun addTask(taskInfo: TaskInfoModel, subtasks: List<SubTaskModel>) {
        taskDao.addTask(taskInfo,subtasks)
    }

    override suspend fun deleteTask(taskInfo: TaskInfoModel, subtasks: List<SubTaskModel>) {
        taskDao.deleteTask(taskInfo, subtasks)
    }

    override suspend fun updateTask(taskInfo: TaskInfoModel, subtasks: List<SubTaskModel>) {
        taskDao.updateTask(taskInfo, subtasks)
    }


    //NoteDaoRepository

    val readAllNotes = noteMapSorterByDate(appDao.getAllNotes())

    override suspend fun deleteNote(note: NoteModel) {
        appDao.deleteNote(note)
    }

    override suspend fun updateNote(note: NoteModel) {
        appDao.updateNote(note)
    }

    override suspend fun saveNote(note: NoteModel) {
        appDao.saveNote(note)
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

    //ApiRepository

    override suspend fun getNews(): LiveData<NewsModel> {
        val responce = withContext(Dispatchers.IO) {
            netService.
            getAllStreetJornal()
        }
        val liveData = MutableLiveData<NewsModel>()
        liveData.value = responce.body()
        return liveData
    }
}