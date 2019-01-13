package ansters.me.todosomeday.data

import androidx.room.Ignore
import ansters.me.todosomeday.R

data class Header(
    var title: String = "",
    @Ignore override var viewType: Int = R.layout.list_header
) : Item