package hongsunghwi.todotaskscreening.data.datasource

import hongsunghwi.todotaskscreening.data.model.EncryptedTodo
import kotlinx.coroutines.flow.Flow

interface TodoDataSource {
    suspend fun addTodo(encryptedContent: ByteArray, regTimestamp: Long, iv: ByteArray)
    suspend fun deleteTodo(id: Int)
    suspend fun updateTodoCompleteTimestamp(id: Int, completeTimestamp: Long)
    fun getTodosOrderByRegTimestamp(): Flow<List<EncryptedTodo>>
    fun getCompletedTodosOrderByCompleteTimestamp(): Flow<List<EncryptedTodo>>
}