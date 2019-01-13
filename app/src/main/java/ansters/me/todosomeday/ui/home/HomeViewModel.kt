package ansters.me.todosomeday.ui.home

import android.util.Log
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
    var isAddTaskCompleted = MutableLiveData<Boolean>().apply { this.value = false }
    var task = MutableLiveData<String>().apply { this.value = "" }

    var dateSelected = MutableLiveData<String>().apply { this.value = "" }
    var dateQuery = MutableLiveData<String>().apply { this.value = "" }

    val todoList: LiveData<List<Todo>> = Transformations.switchMap(dateQuery) { date ->
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
        isAddTaskCompleted.postValue(true)
    }

    fun reset() {
        isEditTaskNotEmpty.postValue(false)
        isCurrentlyAddTask.postValue(false)
        isAddTaskCompleted.postValue(false)
        task.postValue("")
    }
}