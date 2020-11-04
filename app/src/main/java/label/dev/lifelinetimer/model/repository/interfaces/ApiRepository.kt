package label.dev.lifelinetimer.model.repository.interfaces

import androidx.lifecycle.LiveData
import label.dev.lifelinetimer.model.models.apimodels.NewsModel

interface ApiRepository {

    suspend fun getNews(): LiveData<NewsModel>

}