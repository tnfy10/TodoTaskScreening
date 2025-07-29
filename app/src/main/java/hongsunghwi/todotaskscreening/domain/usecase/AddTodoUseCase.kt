package hongsunghwi.todotaskscreening.domain.usecase

import hongsunghwi.todotaskscreening.crypto.AES256Encryption
import hongsunghwi.todotaskscreening.crypto.KeystoreManager
import hongsunghwi.todotaskscreening.domain.repository.TodoRepository
import javax.inject.Inject

class AddTodoUseCase @Inject constructor(
    private val todoRepository: TodoRepository,
    private val keystoreManager: KeystoreManager,
    private val aeS256Encryption: AES256Encryption
) {
    suspend operator fun invoke(content: String) {
        val secretKey = keystoreManager.getSecretKey() ?: keystoreManager.generateSecretKey()
        val (encryptedContent, iv) = aeS256Encryption.encrypt(content.toByteArray(), secretKey)
        todoRepository.addTodo(encryptedContent, iv)
    }
}