package label.dev.lifelinetimer.model.models.dbmodels

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity (tableName = NoteModel.NOTE_TABLENAME)
class NoteModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = NOTE_ID)
    var noteId: Long,

    @ColumnInfo(name = NOTE_TITLE)
    var noteTitle: String,

    @ColumnInfo(name = NOTE_SUBTITLE)
    var noteSubtitle: String?,

    @ColumnInfo(name = NOTE_TEXT)
    var noteText: String?,

    @ColumnInfo(name = NOTE_COLORMARKS)
    var noteColorMark: String,

    @ColumnInfo(name = NOTE_LASTUPDATE)
    var noteLastupdate: String

) : Parcelable
{
    companion object{
        const val NOTE_TABLENAME = "notes_table"
        const val NOTE_ID = "note_id"
        const val NOTE_TITLE = "note_title"
        const val NOTE_SUBTITLE = "note_subtitle"
        const val NOTE_TEXT = "note_text"
        const val NOTE_COLORMARKS = "note_colormarks"
        const val NOTE_LASTUPDATE = "note_lastupdate"
    }
}