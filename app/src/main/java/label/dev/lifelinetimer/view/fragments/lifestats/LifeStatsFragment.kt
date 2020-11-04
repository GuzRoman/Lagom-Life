package label.dev.lifelinetimer.view.fragments.lifestats

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import label.dev.lifelinetimer.R
import label.dev.lifelinetimer.viewmodel.lifestatsvm.LifeStatsViewModel

class LifeStatsFragment : Fragment() {

    private lateinit var viewModel: LifeStatsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lifestats_fragment, container, false)
    }


}