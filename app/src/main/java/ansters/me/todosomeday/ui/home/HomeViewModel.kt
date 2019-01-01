package ansters.me.todosomeday.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ansters.me.todosomeday.data.Todo
import ansters.me.todosomeday.repository.TodoRepository
import ansters.me.todosomeday.util.Util
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val todoRepository: TodoRepository) : ViewModel() {

    var isCurrentlyAddTask = MutableLiveData<Boolean>().apply { this.value = false }
    var isEditTaskNotEmpty = MutableLiveData<Boolean>().apply { this.value = false }
    var task = MutableLiveData<String>().apply { this.value = "" }
    var dateSelected = MutableLiveData<String>().apply { this.value = "" }
    var todo = MutableLiveData<Todo>()

    val todoList: LiveData<List<Todo>> = Transformations.switchMap(dateSelected) { date ->
        todoRepository.getTodosByDate(date)
    }

    fun editTextFocusChange(hasFocus: Boolean) {
        isCurrentlyAddTask.value = hasFocus
        if (!hasFocus) {
            isEditTaskNotEmpty.value = !task.value?.isEmpty()!!
        }
    }

    fun submitNewTodo() {
        val addTodo = Todo(task.value!!, Util.getDateToday(), 0)
        todoRepository.addTodo(addTodo)
        todo.postValue(addTodo)
    }

    fun reset() {
        isEditTaskNotEmpty.value = false
        isCurrentlyAddTask.value = false
        todo.value = Todo("", "", 0)
        task.value = ""
    }
}