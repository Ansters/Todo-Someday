package ansters.me.todosomeday.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import ansters.me.todosomeday.R
import java.text.SimpleDateFormat
import java.util.*

object Util {

    fun hideSoftKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun getDateToday(): String {
        val formatter = SimpleDateFormat("yyy-MM-dd")
        return formatter.format(Date())
    }

    fun getMonthText(context: Context, monthNumber: Int): String {
        val months = context.resources.getStringArray(R.array.months)
        return months[monthNumber-1]
    }

    fun convertFormatDateToTextDate(context: Context, formatDate: String): String {
        val dateParts = formatDate.split("-") // example date format: 2018-12-20, yyyy-mm-dd
        val day = dateParts[2]
        val month = getMonthText(context, dateParts[1].toInt())
        val year = dateParts[0]
        return "$day $month $year"
    }
}
