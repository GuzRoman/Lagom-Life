package label.dev.lifelinetimer.model.repository.interfaces

import androidx.lifecycle.LiveData
import label.dev.lifelinetimer.model.models.dbmodels.tasks.SubTaskModel
import label.dev.lifelinetimer.model.models.dbmodels.tasks.TaskInfoModel
import label.dev.lifelinetimer.model.models.dbmodels.tasks.TaskModel

interface TasksDaoRepository {

    suspend fun addTask(taskInfo: TaskInfoModel, subtasks: List<SubTaskModel>)
    suspend fun deleteTask(taskInfo: TaskInfoModel, subtasks: List<SubTaskModel>)
    suspend fun updateTask(taskInfo: TaskInfoModel, subtasks: List<SubTaskModel>)

}