package hongsunghwi.todotaskscreening.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hongsunghwi.todotaskscreening.local.database.model.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: TodoEntity)

    @Query("DELETE FROM todo WHERE id = :id")
    suspend fun deleteTodo(id: Int)

    @Query("UPDATE todo SET completeTimestamp = :completeTimestamp WHERE id = :id")
    suspend fun updateTodoCompleteTimestamp(id: Int, completeTimestamp: Long)

    @Query("SELECT * FROM todo WHERE completeTimestamp IS NULL ORDER BY regTimestamp DESC")
    fun getTodosOrderByRegTimestamp(): Flow<List<TodoEntity>>

    @Query("SELECT * FROM todo WHERE completeTimestamp IS NOT NULL ORDER BY completeTimestamp DESC")
    fun getCompletedTodosOrderByCompleteTimestamp(): Flow<List<TodoEntity>>
}