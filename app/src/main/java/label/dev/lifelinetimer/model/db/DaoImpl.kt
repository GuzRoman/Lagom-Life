package label.dev.lifelinetimer.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import label.dev.lifelinetimer.model.models.dbmodels.NoteModel


@Database(entities = [NoteModel::class], version = 1)
abstract class DaoImpl : RoomDatabase(){

    abstract fun notesDao(): NotesDao

    companion object {
        @Volatile
        private var databaseInstance: DaoImpl? = null

        fun getDatabaseInstance(context: Context): DaoImpl {
            val tempInstance = databaseInstance
            if (tempInstance != null)
                return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DaoImpl::class.java,
                    "LifeLineTimer.db"
                ).build()
                databaseInstance = instance

                return instance
            }
        }
    }
}