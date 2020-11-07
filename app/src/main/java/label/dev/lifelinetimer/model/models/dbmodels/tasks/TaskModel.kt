package label.dev.lifelinetimer.model.models.dbmodels.tasks

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TaskModel(
    @Embedded var taskInfoModel: TaskInfoModel? = null,
    @Relation(parentColumn = "tasksinfo_id", entityColumn = "subtask_id")
    var tasks: List<SubTaskModel> = ArrayList()
) : Parcelable