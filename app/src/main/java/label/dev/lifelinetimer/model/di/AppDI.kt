package label.dev.lifelinetimer.model.di

import android.app.Application
import label.dev.lifelinetimer.model.api.NetService
import label.dev.lifelinetimer.model.db.DaoImpl
import label.dev.lifelinetimer.model.repository.RepositoryImpl
import label.dev.lifelinetimer.model.repository.interfaces.Repository
import label.dev.lifelinetimer.viewmodel.vmfactories.news.NewsViewModelFactory
import label.dev.lifelinetimer.viewmodel.vmfactories.notes.NoteAddViewModelFactory
import label.dev.lifelinetimer.viewmodel.vmfactories.notes.NoteRedactorViewModelFactory
import label.dev.lifelinetimer.viewmodel.vmfactories.notes.NotesViewModelFactory
import label.dev.lifelinetimer.viewmodel.vmfactories.task.TaskAddViewModelFactory
import label.dev.lifelinetimer.viewmodel.vmfactories.task.TasksViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class AppDI : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@AppDI))

        //DAO
        bind() from singleton { DaoImpl(instance()) }
        bind() from singleton { instance<DaoImpl>().notesDao() }
        bind() from singleton { instance<DaoImpl>().taskDao() }

        //NetService
        bind() from singleton { NetService() }

        //Repository
        bind<Repository>() with singleton { RepositoryImpl(instance(), instance(), instance()) }
        //VMFactories
        //Notes
        bind() from provider { NotesViewModelFactory(instance()) }
        bind() from provider { NoteAddViewModelFactory(instance()) }
        bind() from provider { NoteRedactorViewModelFactory(instance()) }
        //News
        bind() from provider { NewsViewModelFactory(instance()) }
        //Task
        bind() from provider { TasksViewModelFactory(instance()) }
        bind() from provider { TaskAddViewModelFactory(instance()) }

    }

}