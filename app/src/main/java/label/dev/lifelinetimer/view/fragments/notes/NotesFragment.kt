package label.dev.lifelinetimer.view.fragments.notes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.notes_fragment.*
import kotlinx.android.synthetic.main.notes_fragment.view.*
import label.dev.lifelinetimer.R
import label.dev.lifelinetimer.viewmodel.notesvm.NotesViewModel

class NotesFragment : Fragment() {

    private lateinit var notesViewModel: NotesViewModel
    private lateinit var notesRecyclerViewAdapter: NotesRecyclerViewAdapter
    private lateinit var notesRecyclerView: RecyclerView
    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.notes_fragment, container, false)

        notesRecyclerViewAdapter = NotesRecyclerViewAdapter()
        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        gridLayoutManager = GridLayoutManager(requireContext(),2,LinearLayoutManager.VERTICAL, false)
        notesRecyclerView = view.notesRecyclerView
        notesRecyclerView.layoutManager = gridLayoutManager
        notesRecyclerView.setHasFixedSize(true)
        notesRecyclerView.adapter = notesRecyclerViewAdapter

        setData()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesAddBTN.setOnClickListener {
            findNavController().navigate(R.id.action_notesFragment_to_notesDetailsFragment)
        }
    }

    private fun setData(){
        notesViewModel.notesList.observe(viewLifecycleOwner, Observer {list ->
            notesRecyclerViewAdapter.setData(list)
        })
    }


}