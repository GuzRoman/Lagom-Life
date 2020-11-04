package label.dev.lifelinetimer.model.repository.interfaces

import java.time.LocalDateTime

interface TimeRepository {

    fun getCurrentTime(): String
    fun formatTimeToString(time: LocalDateTime): String
    fun formatStringToTime(time: String): LocalDateTime

}