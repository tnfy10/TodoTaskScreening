package hongsunghwi.todotaskscreening.local.datasource

import hongsunghwi.todotaskscreening.data.datasource.TodoDataSource
import hongsunghwi.todotaskscreening.data.model.EncryptedTodo
import hongsunghwi.todotaskscreening.local.database.dao.TodoDao
import hongsunghwi.todotaskscreening.local.database.model.TodoEntity
import hongsunghwi.todotaskscreening.local.datasource.mapper.mapperTodoEntityToEncryptedTodo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoDataSourceImpl @Inject constructor(
    private val todoDao: TodoDao
) : TodoDataSource {
    override suspend fun addTodo(encryptedContent: ByteArray, regTimestamp: Long, iv: ByteArray) {
        todoDao.insertTodo(
            TodoEntity(
                content = encryptedContent,
                regTimestamp = regTimestamp,
                iv = iv
            )
        )
    }

    override suspend fun deleteTodo(id: Int) {
        todoDao.deleteTodo(id)
    }

    override suspend fun updateTodoCompleteTimestamp(
        id: Int,
        completeTimestamp: Long
    ) {
        todoDao.updateTodoCompleteTimestamp(id, completeTimestamp)
    }

    override fun getTodosOrderByRegTimestamp(): Flow<List<EncryptedTodo>> =
        todoDao.getTodosOrderByRegTimestamp().map { todoEntities ->
            todoEntities.map { mapperTodoEntityToEncryptedTodo(it) }
        }

    override fun getCompletedTodosOrderByCompleteTimestamp(): Flow<List<EncryptedTodo>> =
        todoDao.getCompletedTodosOrderByCompleteTimestamp().map { todoEntities ->
            todoEntities.map { mapperTodoEntityToEncryptedTodo(it) }
        }
}