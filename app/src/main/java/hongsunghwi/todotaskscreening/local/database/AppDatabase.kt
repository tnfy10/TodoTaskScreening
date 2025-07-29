package hongsunghwi.todotaskscreening.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import hongsunghwi.todotaskscreening.local.database.dao.TodoDao
import hongsunghwi.todotaskscreening.local.database.model.TodoEntity

@Database(
    entities = [
        TodoEntity::class
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}