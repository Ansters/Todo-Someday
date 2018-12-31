package ansters.me.todosomeday.di.binding

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import ansters.me.todosomeday.util.Util

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("show")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }
}