package label.dev.lifelinetimer.model.db

import androidx.lifecycle.LiveData
import androidx.room.*
import label.dev.lifelinetimer.model.models.dbmodels.tasks.SubTaskModel
import label.dev.lifelinetimer.model.models.dbmodels.tasks.TaskInfoModel
import label.dev.lifelinetimer.model.models.dbmodels.tasks.TaskModel

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(taskInfo: TaskInfoModel, subtasks: List<SubTaskModel>?)

    @Transaction
    @Query("SELECT * FROM tasksinfo_tablename")
    fun readAllTasks(): LiveData<List<TaskModel>>

    @Delete
    suspend fun deleteTask(taskInfo: TaskInfoModel, subtasks: List<SubTaskModel>?)

    @Update
    suspend fun updateTask(taskInfo: TaskInfoModel, subtasks: List<SubTaskModel>?)
}