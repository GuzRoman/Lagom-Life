package label.dev.lifelinetimer.view.fragments.notes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.noteredactor_fragment.*
import kotlinx.android.synthetic.main.noteredactor_fragment.view.*
import label.dev.lifelinetimer.R
import label.dev.lifelinetimer.model.models.dbmodels.notes.NoteModel
import label.dev.lifelinetimer.view.MainActivity
import label.dev.lifelinetimer.view.dialogs.ColorMarkDialogFragment
import label.dev.lifelinetimer.viewmodel.notesvm.NoteRedactorViewModel

class NoteRedactorFragment : Fragment() {

    private val args by navArgs<NoteRedactorFragmentArgs>()

    private lateinit var noteRedactorViewModel: NoteRedactorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.noteredactor_fragment, container, false)

        (requireActivity() as MainActivity).bottomNavigationView.visibility = View.GONE

        noteRedactorViewModel = ViewModelProvider(this).get(NoteRedactorViewModel::class.java)

        view.noteRedactorEDTitle.setText(args.selectedItem.noteTitle)
        view.noteRedactorEDSubtitle.setText(args.selectedItem.noteSubtitle)
        view.noteRedactorEDText.setText(args.selectedItem.noteText)
        view.noteRedactorEditColorMark.setBackgroundResource(setColorMark(args.selectedItem.noteColorMark))
        view.noteRedactorEditColorMark.text=args.selectedItem.noteColorMark

        view.noteRedactorSaveBTN.setOnClickListener {
            updateItem()
            findNavController().navigate(R.id.action_noteRedactorFragment_to_notesFragment)
        }

        view.noteRedactorDeleteBTN.setOnClickListener {
            deleteNote()
            findNavController().navigate(R.id.action_noteRedactorFragment_to_notesFragment)
        }

        view.noteRedactorEditColorMark.setOnClickListener {
            val dialog = ColorMarkDialogFragment()
            dialog.show(fragment.parentFragmentManager, "Color Marks")
            dialog.onColorSelected = { color ->
                view.noteRedactorEditColorMark.setBackgroundResource(setColorMark(color))
            }
        }

        return view
    }

    private fun updateItem() {

        val noteTitle = noteRedactorEDTitle.text.toString()
        val noteSubtitle = noteRedactorEDSubtitle.text.toString()
        val noteText = noteRedactorEDText.text.toString()
        val noteColorMark = noteRedactorEditColorMark.text.toString()

        if (noteTitle.isEmpty()) {
            Toast.makeText(requireContext(), "Заполните заголовок", Toast.LENGTH_SHORT).show()
        } else {
            val updatedNote = NoteModel(
                args.selectedItem.noteId,
                noteTitle,
                noteSubtitle,
                noteText,
                noteColorMark,
                noteRedactorViewModel.updateTime()
            )
            noteRedactorViewModel.updateNote(updatedNote)
            Toast.makeText(requireContext(), "Заметка обновлена", Toast.LENGTH_SHORT).show()
        }
    }


    private fun deleteNote() {
        noteRedactorViewModel.deleteNote(args.selectedItem)
        Toast.makeText(requireContext(), "Заметка удалена", Toast.LENGTH_SHORT).show()
    }

    private fun setColorMark(color: String): Int{
        val backGround = noteRedactorViewModel.findBackGround(color)
        view?.noteRedactorEditColorMark?.text = color
        return backGround
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (requireActivity() as MainActivity).bottomNavigationView.visibility = View.VISIBLE
    }

}