package label.dev.lifelinetimer.model.models.apimodels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsModel(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
): Parcelable