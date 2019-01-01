package ansters.me.todosomeday.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ansters.me.todosomeday.R
import ansters.me.todosomeday.data.Todo
import ansters.me.todosomeday.databinding.ListTodoBinding
import ansters.me.todosomeday.ui.common.DataBoundViewHolder

class TodoListAdapter(

) : ListAdapter<Todo, DataBoundViewHolder<ListTodoBinding>>(
    AsyncDifferConfig.Builder(TodoListDiffCallback()).build()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder<ListTodoBinding> {
        val binding = DataBindingUtil.inflate<ListTodoBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_todo,
            parent,
            false
        )
        return DataBoundViewHolder<ListTodoBinding>(binding)
    }

    override fun onBindViewHolder(holder: DataBoundViewHolder<ListTodoBinding>, position: Int) {

    }

    class TodoListDiffCallback : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.task == newItem.task && oldItem.status == newItem.status && oldItem.date == newItem.date
        }
    }

}