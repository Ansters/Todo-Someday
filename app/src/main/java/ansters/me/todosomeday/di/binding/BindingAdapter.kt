package ansters.me.todosomeday.di.binding

import android.view.View
import android.widget.CheckBox
import androidx.databinding.BindingAdapter
import ansters.me.todosomeday.R

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("show")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("checked")
    fun todoChecked(checkBox: CheckBox, status: Int) {
        checkBox.isChecked = status == 1
    }

    @JvmStatic
    @BindingAdapter("completedTodo")
    fun todoCompleted(view: View, status: Int) {
        if (status == 0) {
            view.setBackgroundResource(R.color.background_active_task)
        }
        else {
            view.setBackgroundResource(R.color.background_main)
        }
    }
}