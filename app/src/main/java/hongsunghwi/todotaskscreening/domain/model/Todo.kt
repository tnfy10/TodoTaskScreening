package hongsunghwi.todotaskscreening.domain.model

import java.time.LocalDateTime

data class Todo(
    val id: Int,
    val content: String,
    val regDateTime: LocalDateTime,
    val completeDateTime: LocalDateTime?
)
