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
    private val todoClickCallback: ((Todo) -> Unit)
) : ListAdapter<Item, DataBoundViewHolder<ViewDataBinding>>(
    AsyncDifferConfig.Builder(TodoListDiffCallback()).build()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder<ViewDataBinding> {
        if (viewType == R.layout.list_todo) {
            val binding = DataBindingUtil.inflate<ListTodoBinding>(
                LayoutInflater.from(parent.context),
                R.layout.list_todo,
                parent,
                false
            )
            return DataBoundViewHolder(binding)
        }
        else {
            val binding = DataBindingUtil.inflate<ListHeaderBinding>(
                LayoutInflater.from(parent.context),
                R.layout.list_header,
                parent,
                false
            )
            return DataBoundViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: DataBoundViewHolder<ViewDataBinding>, position: Int) {
        val item = getItem(position)
        if (holder.binding is ListTodoBinding && item is Todo) {
            holder.binding.todo = item
            holder.binding.cbFinish.setOnClickListener {
                todoClickCallback(item)
            }
        }
        else if (holder.binding is ListHeaderBinding && item is Header) {
            holder.binding.header = item
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
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