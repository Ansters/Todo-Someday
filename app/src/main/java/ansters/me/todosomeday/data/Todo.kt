package ansters.me.todosomeday.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import ansters.me.todosomeday.R

@Entity
data class Todo(
    var task: String = "",
    var date: String = "",
    var status: Int = 0,
    @Ignore override var viewType: Int = R.layout.list_todo
) : Item {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}