package label.dev.lifelinetimer.view.fragments.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item.view.*
import label.dev.lifelinetimer.R
import label.dev.lifelinetimer.model.models.dbmodels.notes.ColorMarks
import label.dev.lifelinetimer.model.models.dbmodels.notes.NoteModel


class NotesRecyclerViewAdapter: RecyclerView.Adapter<NotesRecyclerViewAdapter.NotesViewHolder>() {

    private var notes = mutableListOf<NoteModel>()

    class NotesViewHolder(item: View) : RecyclerView.ViewHolder(item){
        var title: TextView = item.findViewById(R.id.noteTitle)
        var subTitle: TextView = item.findViewById(R.id.noteSubtitle)
        var colorMark: View = item.findViewById(R.id.noteColorMark)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NotesViewHolder(view)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notes[position]
        holder.title.text = note.noteTitle
        holder.subTitle.text = note.noteSubtitle
        holder.colorMark.setBackgroundResource(setNoteCMBackground(note.noteColorMark))

        holder.itemView.notesRawLayout.setOnClickListener {
            val action = NotesFragmentDirections.actionNotesFragmentToNoteRedactorFragment(note)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(setNotes: List<NoteModel>){
        notes.addAll(setNotes)
        notifyDataSetChanged()
    }

    private fun setNoteCMBackground(backgroundColor: String?) = when(backgroundColor){
            ColorMarks.BLUE.name -> R.drawable.cm_blue
            ColorMarks.GREEN.name -> R.drawable.cm_green
            ColorMarks.PURPLE.name -> R.drawable.cm_purple
            ColorMarks.RED.name -> R.drawable.cm_red
            ColorMarks.WHITE.name -> R.drawable.cm_white
            ColorMarks.YELLOW.name -> R.drawable.cm_yellow
            else -> R.drawable.cm_white
        }

}