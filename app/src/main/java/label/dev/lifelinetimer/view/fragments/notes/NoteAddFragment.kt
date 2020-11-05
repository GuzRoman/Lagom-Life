package label.dev.lifelinetimer.view.fragments.notes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.noteadd_fragment.*
import kotlinx.android.synthetic.main.noteadd_fragment.view.*
import label.dev.lifelinetimer.R
import label.dev.lifelinetimer.model.models.dbmodels.NoteModel
import label.dev.lifelinetimer.view.MainActivity
import label.dev.lifelinetimer.view.dialogs.ColorMarkDialogFragment
import label.dev.lifelinetimer.viewmodel.notesvm.NoteAddViewModel

class NoteAddFragment : Fragment() {

    private lateinit var noteAddViewModel: NoteAddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.noteadd_fragment, container, false)

        (requireActivity() as MainActivity).bottomNavigationView.visibility = View.GONE

        noteAddViewModel = ViewModelProvider(this).get(NoteAddViewModel::class.java)

        view.noteSaveBTN.setOnClickListener {
            insertDataToDatabase()
        }

        view.noteEditColorMark.setOnClickListener {
            val dialog = ColorMarkDialogFragment()
            dialog.show(fragment.parentFragmentManager, "Color Marks")
            dialog.onColorSelected = { color ->
                changeColorWithDialogResponse(color)
            }
        }

        return view
    }

    private fun insertDataToDatabase() {
        val noteTitle = noteEDTitle.text.toString()
        val noteSubtitle = noteEDSubtitle.text.toString()
        val noteText = noteEDSubtitle.text.toString()
        val noteMark = noteEditColorMark.text.toString()
        if (noteTitle.isEmpty()) {
            Toast.makeText(requireContext(), "Заполните заголовок", Toast.LENGTH_SHORT).show()
        } else {
            val note = NoteModel(
                0,
                noteTitle,
                noteSubtitle,
                noteText,
                noteMark,
                noteAddViewModel.getCurrentTime()
            )
            saveNote(note)
            Toast.makeText(requireContext(), "Заметка сохранена", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_notesDetailsFragment_to_notesFragment)
        }
    }

    private fun saveNote(note: NoteModel) {
        noteAddViewModel.saveNote(note)
    }

    private fun changeColorWithDialogResponse(color: String) {
        val backGround = noteAddViewModel.findBackGround(color)
        view?.noteEditColorMark?.text = color
        view?.noteEditColorMark?.setBackgroundResource(backGround)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (requireActivity() as MainActivity).bottomNavigationView.visibility = View.VISIBLE
    }

}