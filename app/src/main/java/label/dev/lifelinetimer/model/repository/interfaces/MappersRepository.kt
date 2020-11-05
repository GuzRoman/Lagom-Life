package label.dev.lifelinetimer.model.repository.interfaces

import androidx.lifecycle.LiveData
import label.dev.lifelinetimer.model.models.dbmodels.notes.NoteModel

interface MappersRepository {

    fun noteMapSorterByDate(list: LiveData<List<NoteModel>>): LiveData<List<NoteModel>>

}
