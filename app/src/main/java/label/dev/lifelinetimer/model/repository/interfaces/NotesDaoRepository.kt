package label.dev.lifelinetimer.model.repository.interfaces

import label.dev.lifelinetimer.model.models.dbmodels.NoteModel

interface NotesDaoRepository {

    suspend fun deleteNote(note: NoteModel)

    suspend fun updateNote(note: NoteModel)

    suspend fun saveNote(note: NoteModel)

}