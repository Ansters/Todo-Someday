package ansters.me.todosomeday.util

import ansters.me.todosomeday.data.Todo

object TestUtil {

    fun createTodo(
        task: String,
        date: String,
        status: Int
    ) = Todo(task, date, status)

    fun createRandomTodoByDate(count: Int, date: List<String>): List<Todo> {
        var todos = ArrayList<Todo>()
        for (i in 0..count) {
            todos.add(createTodo("Random task", date.random(), 0))
        }
        return todos
    }

}