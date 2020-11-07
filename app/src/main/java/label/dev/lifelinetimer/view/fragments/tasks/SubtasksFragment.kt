package label.dev.lifelinetimer.view.fragments.tasks

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import label.dev.lifelinetimer.R
import label.dev.lifelinetimer.viewmodel.tasksvm.SubtasksViewModel

class SubtasksFragment : Fragment() {

    private lateinit var viewModel: SubtasksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.subtasks_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SubtasksViewModel::class.java)
        // TODO: Use the ViewModel
    }

}