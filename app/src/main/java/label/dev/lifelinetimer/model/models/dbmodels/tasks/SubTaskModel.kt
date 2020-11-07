package label.dev.lifelinetimer.model.models.dbmodels.tasks

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = SubTaskModel.SUBTASK_TABLENAME)
data class SubTaskModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SUBTASK_ID)
    var id: Long,

    @ColumnInfo(name = SUBTASK_TITLE)
    var title: String,

    @ColumnInfo(name = SUBTASK_ISDONE)
    var isDone: Boolean = false,

    @ColumnInfo(name = SUBTASK_DISCRIPTION)
    var discription: String?,

    @ColumnInfo(name = SUBTASK_STATE)
    var state: String = SubTaskState.TODO.name

) : Parcelable {
    companion object {
        const val SUBTASK_TABLENAME = "subtasks_table"
        const val SUBTASK_ID = "subtask_id"
        const val SUBTASK_TITLE = "subtask_title"
        const val SUBTASK_ISDONE = "subtask_isdone"
        const val SUBTASK_DISCRIPTION = "subtask_discription"
        const val SUBTASK_STATE = "subtask_state"
    }
}