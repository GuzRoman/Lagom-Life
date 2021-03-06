package label.dev.lifelinetimer.view.fragments.vcrunews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.vcrnews_fragment.view.*
import label.dev.lifelinetimer.R
import label.dev.lifelinetimer.viewmodel.newsvm.NewsViewModel
import label.dev.lifelinetimer.viewmodel.vmfactories.news.NewsViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class NewsFragment : Fragment(), KodeinAware {
    override val kodein: Kodein by closestKodein()

    private val newsViewModelFactory: NewsViewModelFactory by instance()

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsRecyclerViewAdapter: NewsRecyclerViewAdapter
    private lateinit var newsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.vcrnews_fragment, container, false)

        newsRecyclerViewAdapter = NewsRecyclerViewAdapter()
        newsViewModel = ViewModelProvider(this, newsViewModelFactory).get(NewsViewModel::class.java)
        newsRecyclerView = view.newsRecyclerView
        newsRecyclerView.adapter = newsRecyclerViewAdapter

        setData()

        return view
    }

    private fun setData(){
        newsViewModel.newsData.observe(viewLifecycleOwner, Observer {list ->
            newsRecyclerViewAdapter.setData(list.articles)
        })
    }
}