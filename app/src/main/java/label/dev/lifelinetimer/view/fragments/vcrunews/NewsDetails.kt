package label.dev.lifelinetimer.view.fragments.vcrunews

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.newsdetails_fragment.view.*
import label.dev.lifelinetimer.R
import label.dev.lifelinetimer.viewmodel.vcrunewsvm.NewsDetailsViewModel

class NewsDetails : Fragment() {

    private val args by navArgs<NewsDetailsArgs>()

    private lateinit var newsDetailsViewModel: NewsDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.newsdetails_fragment, container, false)

        view.newsDetailsTitle.text = args.selectedNews.title
        view.newsDetailsSubTitle.text = args.selectedNews.description
        Glide.with(requireContext()).load(args.selectedNews.urlToImage).into(view.newsDetailsImage)

    return view
    }

}