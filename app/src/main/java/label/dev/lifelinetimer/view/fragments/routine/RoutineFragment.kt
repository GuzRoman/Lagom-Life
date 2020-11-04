package label.dev.lifelinetimer.view.fragments.routine

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import label.dev.lifelinetimer.R
import label.dev.lifelinetimer.viewmodel.routinevm.RoutineViewModel

class RoutineFragment : Fragment() {

    private lateinit var viewModel: RoutineViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.routine_fragment, container, false)
    }

}