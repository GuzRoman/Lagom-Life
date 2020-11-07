package label.dev.lifelinetimer.model.models.dbmodels.tasks

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class SubTaskState: Parcelable {

    TODO,
    INPROGRESS,
    DONE

}