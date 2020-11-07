package label.dev.lifelinetimer.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import label.dev.lifelinetimer.model.models.dbmodels.notes.NoteModel
import label.dev.lifelinetimer.model.models.dbmodels.tasks.SubTaskModel
import label.dev.lifelinetimer.model.models.dbmodels.tasks.TaskInfoModel


@Database(entities = [NoteModel::class,
    TaskInfoModel::class,
    SubTaskModel::class],
    version = 1)

abstract class DaoImpl : RoomDatabase() {

    abstract fun taskDao(): TaskDao
    abstract fun notesDao(): AppDao


    companion object{
        @Volatile private var instanse: DaoImpl? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instanse
            ?: synchronized(LOCK){
                instanse
                    ?: buildDatabase(
                        context
                    ).also { instanse = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                DaoImpl::class.java, "LifeLineTimer.db")
                .build()
    }


//    companion object {
//        @Volatile
//        private var databaseInstance: DaoImpl? = null
//
//        fun getDatabaseInstance(context: Context): DaoImpl {
//            val tempInstance = databaseInstance
//            if (tempInstance != null)
//                return tempInstance
//
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    DaoImpl::class.java,
//                    "LifeLineTimer.db"
//                ).build()
//                databaseInstance = instance
//
//                return instance
//            }
//        }
//    }
}