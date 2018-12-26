package ansters.me.todosomeday.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ansters.me.todosomeday.data.Todo
import ansters.me.todosomeday.repository.TodoRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(todoRepository: TodoRepository) : ViewModel() {
    var isCurrentlyAddTask = MutableLiveData<Boolean>().apply { this.value = false }
    var isEditTaskNotEmpty = MutableLiveData<Boolean>().apply { this.value = false }
    var todo = MutableLiveData<Todo>()

    fun addTaskFocusChange(hasFocus: Boolean) {
        isCurrentlyAddTask.value = hasFocus
    }
}