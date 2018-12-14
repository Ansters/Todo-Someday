package ansters.me.todosomeday.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    val task: String,
    val date: String,
    val status: Int
) {
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
}