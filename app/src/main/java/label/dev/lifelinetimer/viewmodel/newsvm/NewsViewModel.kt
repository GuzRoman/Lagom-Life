package label.dev.lifelinetimer.viewmodel.newsvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import label.dev.lifelinetimer.model.models.apimodels.NewsModel
import label.dev.lifelinetimer.model.repository.interfaces.Repository

class NewsViewModel(private val repository: Repository) : ViewModel() {

    val newsData: MutableLiveData<NewsModel> = MutableLiveData()

    init {
        getData()
    }

    private fun getData() = viewModelScope.launch{
        fetchData()
    }

    private suspend fun fetchData(){
        val response = repository.getNews()
        newsData.value = response.value
    }
}