package label.dev.lifelinetimer.model.models.dbmodels.tasks

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TaskInfoModel.TASKSINFO_TABLENAME)
data class TaskInfoModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = TASKSINFO_ID)
    var id: Long,

    @ColumnInfo(name = TASKSINFO_TITLE)
    var title: String,

    @ColumnInfo(name = TASKSINFO_SUBTITLE)
    var subTitle: String,

    @ColumnInfo(name = TASKSINFO_ENDDATE)
    var endDate: String,

    @ColumnInfo(name = TASKSINFO_NOTDONESUBTASKS)
    var notDoneSubtasks: Int,

    @ColumnInfo(name = TASKSINFO_SUBTASKSCOUNT)
    var subtaskCount: Int

): Parcelable
{
    companion object{
        const val TASKSINFO_TABLENAME = "tasksinfo_tablename"
        const val TASKSINFO_ID = "tasksinfo_id"
        const val TASKSINFO_TITLE = "tasksinfo_title"
        const val TASKSINFO_SUBTITLE = "tasksinfo_subtitle"
        const val TASKSINFO_ENDDATE = "tasksinfo_enddate"
        const val TASKSINFO_NOTDONESUBTASKS = "tasksinfo_notdonesubtasks"
        const val TASKSINFO_SUBTASKSCOUNT = "tasksinfo_subtaskscount"
    }
}