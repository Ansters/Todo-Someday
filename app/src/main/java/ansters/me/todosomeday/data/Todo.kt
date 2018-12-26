package ansters.me.todosomeday.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    var task: String,
    var date: String,
    var status: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}