package hongsunghwi.todotaskscreening.local.datasource.mapper

import hongsunghwi.todotaskscreening.data.model.EncryptedTodo
import hongsunghwi.todotaskscreening.local.database.model.TodoEntity
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

fun mapperTodoEntityToEncryptedTodo(todoEntity: TodoEntity): EncryptedTodo {
    val defaultZoneId = ZoneId.systemDefault()
    val regInstant = Instant.ofEpochSecond(todoEntity.regTimestamp)
    val completeInstant = todoEntity.completeTimestamp?.let { Instant.ofEpochSecond(it) }
    val regDateTime = LocalDateTime.ofInstant(regInstant, defaultZoneId)
    val completeDateTime = completeInstant?.let { LocalDateTime.ofInstant(it, defaultZoneId) }

    return EncryptedTodo(
        id = todoEntity.id,
        encryptedContent = todoEntity.content,
        regDateTime = regDateTime,
        completeDateTime = completeDateTime,
        iv = todoEntity.iv
    )
}