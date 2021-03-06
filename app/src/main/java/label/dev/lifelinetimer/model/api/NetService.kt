package label.dev.lifelinetimer.model.api

import label.dev.lifelinetimer.model.models.apimodels.NewsModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//http://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=392b1990c7794786a8fb9e13cfbe09ed
//7760361430b890d90327f2c19a5d8dbd9a7c900c9f8b25c98ca8fa49ddbdd2df
//https://api.dtf.ru/v1.8/
//https://api.vc.ru/v1.8/timeline/mainpage/recent
//https://api.vc.ru/v1.8/news/default/recent
const val KEY = "392b1990c7794786a8fb9e13cfbe09ed"
const val BASE_URL = "http://newsapi.org/v2/"


interface NetService {
    @GET("top-headlines")
    suspend fun getAllStreetJornal(
        @Query("country") country: String = "us",
        @Query("category") category: String = "business"
    ): Response<NewsModel>


    companion object {
        operator fun invoke(): NetService {
            //Создаём интерсептор, куда встраиваем KEY для запросов
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("apiKey", KEY)
                    .build()

                val requirest = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(requirest)

            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(NetService::class.java)
        }
    }
}