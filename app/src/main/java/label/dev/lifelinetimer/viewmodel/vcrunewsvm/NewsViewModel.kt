package label.dev.lifelinetimer.viewmodel.vcrunewsvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import label.dev.lifelinetimer.model.api.NetService
import label.dev.lifelinetimer.model.db.DaoImpl
import label.dev.lifelinetimer.model.models.apimodels.NewsModel
import label.dev.lifelinetimer.model.repository.RepositoryImpl

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RepositoryImpl
    val newsData: MutableLiveData<NewsModel> = MutableLiveData()

    init {
        val dao = DaoImpl.getDatabaseInstance(application).notesDao()
        repository = RepositoryImpl(dao, NetService())
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