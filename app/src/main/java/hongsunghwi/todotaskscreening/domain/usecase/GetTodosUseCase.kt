package hongsunghwi.todotaskscreening.domain.usecase

import hongsunghwi.todotaskscreening.crypto.AES256Encryption
import hongsunghwi.todotaskscreening.crypto.KeystoreManager
import hongsunghwi.todotaskscreening.domain.mapper.mapperEncryptedTodoToTodo
import hongsunghwi.todotaskscreening.domain.model.Todo
import hongsunghwi.todotaskscreening.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTodosUseCase @Inject constructor(
    private val todoRepository: TodoRepository,
    private val keystoreManager: KeystoreManager,
    private val aeS256Encryption: AES256Encryption
) {

    operator fun invoke(): Flow<List<Todo>> = todoRepository.getTodos().map { encryptedTodos ->
        val secretKey = keystoreManager.getSecretKey()
        if (secretKey != null) {
            encryptedTodos.map {
                mapperEncryptedTodoToTodo(it, aeS256Encryption, secretKey)
            }
        } else {
            emptyList()
        }
    }
}