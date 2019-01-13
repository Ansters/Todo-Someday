package ansters.me.todosomeday.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ansters.me.todosomeday.R
import ansters.me.todosomeday.data.Header
import ansters.me.todosomeday.data.Item
import ansters.me.todosomeday.data.Todo
import ansters.me.todosomeday.databinding.ListHeaderBinding
import ansters.me.todosomeday.databinding.ListTodoBinding
import ansters.me.todosomeday.ui.common.DataBoundViewHolder

class TodoListAdapter(

) : ListAdapter<Item, TodoListAdapter.TodoViewHolder>(
    AsyncDifferConfig.Builder(TodoListDiffCallback()).build()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListAdapter.TodoViewHolder {
        if (viewType == R.layout.list_todo) {
            val binding = DataBindingUtil.inflate<ListTodoBinding>(
                LayoutInflater.from(parent.context),
                R.layout.list_todo,
                parent,
                false
            )
            return TodoViewHolder(binding)
        }
        else {
            val binding = DataBindingUtil.inflate<ListHeaderBinding>(
                LayoutInflater.from(parent.context),
                R.layout.list_header,
                parent,
                false
            )
            return TodoViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: TodoListAdapter.TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    class TodoViewHolder : RecyclerView.ViewHolder {

        lateinit var todoBinding: ListTodoBinding
        lateinit var headerBinding: ListHeaderBinding

        constructor(todoBinding: ListTodoBinding) : super(todoBinding.root) {
           this.todoBinding = todoBinding
        }
        constructor(headerBinding: ListHeaderBinding) : super(headerBinding.root) {
            this.headerBinding = headerBinding
        }

        fun bind(item: Item) {
            if (item is Todo) {
                todoBinding.todo = item
            }
            else if (item is Header) {
                headerBinding.header = item
            }
        }

    }

    class TodoListDiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            if (oldItem is Todo && newItem is Todo) {
                return oldItem.id == newItem.id
            }
            else if (oldItem is Header && newItem is Header) {
                return oldItem.title == newItem.title
            }
            return false
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            if (oldItem is Todo && newItem is Todo) {
                return oldItem.task == newItem.task && oldItem.status == newItem.status && oldItem.date == newItem.date
            }
            else if (oldItem is Header && newItem is Header) {
                return oldItem.title == newItem.title
            }
            return false
        }
    }

}