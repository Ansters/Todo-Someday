package ansters.me.todosomeday.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ansters.me.todosomeday.data.Todo
import ansters.me.todosomeday.repository.TodoRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(todoRepository: TodoRepository) : ViewModel() {
    var isCurrentlyAddTask = MutableLiveData<Boolean>().apply { this.value = false }
    var isEditTaskNotEmpty = MutableLiveData<Boolean>().apply { this.value = false }
    var todo = MutableLiveData<Todo>().apply { this.value = Todo("", "", 0) }
    var task = MutableLiveData<String>().apply { this.value = "" }

    fun editTextFocusChange(hasFocus: Boolean) {
        isCurrentlyAddTask.value = hasFocus
        if (!hasFocus) {
            isEditTaskNotEmpty.value = !task.value?.isEmpty()!!
        }
    }
}