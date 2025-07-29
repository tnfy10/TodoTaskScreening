package hongsunghwi.todotaskscreening.domain.repository

import hongsunghwi.todotaskscreening.data.model.EncryptedTodo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun addTodo(encryptedContent: ByteArray, iv: ByteArray)
    suspend fun deleteTodo(id: Int)
    suspend fun setTodoCompleted(id: Int)
    fun getTodos(): Flow<List<EncryptedTodo>>
    fun getCompletedTodos(): Flow<List<EncryptedTodo>>
}