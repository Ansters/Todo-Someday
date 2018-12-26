package ansters.me.todosomeday.repository

import androidx.lifecycle.LiveData
import ansters.me.todosomeday.data.Todo
import ansters.me.todosomeday.db.TodoDao
import javax.inject.Inject

/**
 * Simple Repository that load from DB only
 */
class TodoRepository @Inject constructor(
    private val todoDao: TodoDao
) {
    fun addTodo(todo: Todo) {
        todoDao.insert(todo)
    }

    fun removeTodo(todo: Todo) {
        todoDao.delete(todo)
    }

    fun updateTodo(todo: Todo) {
        todoDao.update(todo)
    }

    fun getTodo(id: Long) : LiveData<Todo> {
        return todoDao.find(id)
    }

    fun getTodosByDate(date: String) : LiveData<List<Todo>> {
        return todoDao.findByDate(date)
    }
}