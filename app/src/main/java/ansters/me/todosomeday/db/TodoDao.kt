package ansters.me.todosomeday.db

import androidx.lifecycle.LiveData
import androidx.room.*
import ansters.me.todosomeday.data.Todo

@Dao
abstract class TodoDao {

    @Insert
    abstract fun insert(todo: Todo): Long

    @Query("SELECT id, task, date, status FROM todo WHERE id = :id")
    abstract fun find(id: Long): LiveData<Todo>

    @Update
    abstract fun update(todo: Todo)

    @Query("SELECT id, task, date, status FROM todo WHERE date = :date ORDER BY status ASC")
    abstract fun findByDate(date: String): LiveData<List<Todo>>

    @Delete
    abstract fun delete(todo: Todo)

}