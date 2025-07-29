package hongsunghwi.todotaskscreening.domain.mapper

import hongsunghwi.todotaskscreening.crypto.AES256Encryption
import hongsunghwi.todotaskscreening.data.model.EncryptedTodo
import hongsunghwi.todotaskscreening.domain.model.Todo
import javax.crypto.SecretKey

fun mapperEncryptedTodoToTodo(
    encryptedTodo: EncryptedTodo,
    aeS256Encryption: AES256Encryption,
    secretKey: SecretKey
): Todo {
    val decryptedContent =
        aeS256Encryption.decrypt(encryptedTodo.encryptedContent, encryptedTodo.iv, secretKey)
    val content = String(decryptedContent)

    return Todo(
        id = encryptedTodo.id,
        content = content,
        regDateTime = encryptedTodo.regDateTime,
        completeDateTime = encryptedTodo.completeDateTime
    )
}