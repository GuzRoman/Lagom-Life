package label.dev.lifelinetimer.view.fragments.vcrunews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.newsdetails_fragment.view.*
import label.dev.lifelinetimer.R
import label.dev.lifelinetimer.view.MainActivity

class NewsDetails : Fragment() {

    private val args by navArgs<NewsDetailsArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.newsdetails_fragment, container, false)

        (requireActivity() as MainActivity).bottomNavigationView.visibility = View.GONE

        view.newsDetailsTitle.text = args.selectedNews.title
        view.newsDetailsSubTitle.text = args.selectedNews.description
        Glide.with(requireContext())
            .load(args.selectedNews.urlToImage)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(view.newsDetailsImage)

    return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (requireActivity() as MainActivity).bottomNavigationView.visibility = View.VISIBLE
    }

}