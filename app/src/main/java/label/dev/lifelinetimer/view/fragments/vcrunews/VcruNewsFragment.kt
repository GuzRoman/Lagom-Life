package label.dev.lifelinetimer.view.fragments.vcrunews

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import label.dev.lifelinetimer.R
import label.dev.lifelinetimer.viewmodel.vcrunewsvm.VcruNewsViewModel

class VcruNewsFragment : Fragment() {

    private lateinit var viewModel: VcruNewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.vcrnews_fragment, container, false)
    }

}