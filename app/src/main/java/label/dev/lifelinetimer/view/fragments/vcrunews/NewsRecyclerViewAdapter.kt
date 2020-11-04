package label.dev.lifelinetimer.view.fragments.vcrunews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.news_item.view.*
import label.dev.lifelinetimer.R
import label.dev.lifelinetimer.model.models.apimodels.Article

class NewsRecyclerViewAdapter: RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder>() {

    val news = mutableListOf<Article>()


    class NewsViewHolder(item: View): RecyclerView.ViewHolder(item){
        var title: TextView = item.findViewById(R.id.newsItemTitle)
        var image: ImageView = item.findViewById(R.id.newsItemImage)
    }

    override fun getItemCount() = news.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = news[position]

        holder.title.text = article.title
        Glide.with(holder.itemView.context).load(article.urlToImage).into(holder.image)

        holder.itemView.newsRawLayout.setOnClickListener {
            val action = NewsFragmentDirections.actionNewsFragmentToNewsDetails(article)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(setNews: List<Article>){
        news.addAll(setNews)
        notifyDataSetChanged()
    }
}