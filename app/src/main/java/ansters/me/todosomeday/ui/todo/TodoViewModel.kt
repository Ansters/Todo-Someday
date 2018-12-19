package ansters.me.todosomeday.ui.todo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ansters.me.todosomeday.repository.TodoRepository
import javax.inject.Inject

class TodoViewModel @Inject constructor(todoRepository: TodoRepository) : ViewModel() {

    var isCurrentlyAddTask : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    var isEditTaskNotEmpty: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    fun addTaskFocusChange(hasFocus: Boolean) {
        isCurrentlyAddTask.value = hasFocus

    }
}