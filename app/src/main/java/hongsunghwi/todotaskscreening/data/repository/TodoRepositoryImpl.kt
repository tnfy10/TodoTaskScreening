package hongsunghwi.todotaskscreening.data.repository

import hongsunghwi.todotaskscreening.data.datasource.TodoDataSource
import hongsunghwi.todotaskscreening.data.model.EncryptedTodo
import hongsunghwi.todotaskscreening.domain.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.time.ZoneOffset
import java.time.ZonedDateTime
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val todoDataSource: TodoDataSource
) : TodoRepository {
    override suspend fun addTodo(encryptedContent: ByteArray, iv: ByteArray) {
        val nowZonedDateTimeUTC = ZonedDateTime.now(ZoneOffset.UTC)
        val timestamp = nowZonedDateTimeUTC.toEpochSecond()

        withContext(Dispatchers.IO) {
            todoDataSource.addTodo(encryptedContent, timestamp, iv)
        }
    }

    override suspend fun deleteTodo(id: Int) {
        withContext(Dispatchers.IO) {
            todoDataSource.deleteTodo(id)
        }
    }

    override suspend fun setTodoCompleted(id: Int) {
        val nowZonedDateTimeUTC = ZonedDateTime.now(ZoneOffset.UTC)
        val timestamp = nowZonedDateTimeUTC.toEpochSecond()

        withContext(Dispatchers.IO) {
            todoDataSource.updateTodoCompleteTimestamp(id, timestamp)
        }
    }

    override fun getTodos(): Flow<List<EncryptedTodo>> =
        todoDataSource.getTodosOrderByRegTimestamp().flowOn(Dispatchers.IO)

    override fun getCompletedTodos(): Flow<List<EncryptedTodo>> =
        todoDataSource.getCompletedTodosOrderByCompleteTimestamp().flowOn(Dispatchers.IO)
}