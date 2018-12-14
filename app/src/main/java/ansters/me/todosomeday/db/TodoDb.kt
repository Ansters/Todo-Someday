package ansters.me.todosomeday.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ansters.me.todosomeday.data.Todo

@Database(
    entities = [Todo::class],
    version = 1,
    exportSchema = false
)
abstract class TodoDb : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}