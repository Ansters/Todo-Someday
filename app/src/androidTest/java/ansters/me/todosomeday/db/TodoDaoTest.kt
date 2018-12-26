package ansters.me.todosomeday.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import ansters.me.todosomeday.data.Todo
import ansters.me.todosomeday.util.LiveDataTestUtil
import ansters.me.todosomeday.util.LiveDataTestUtil.getValue
import ansters.me.todosomeday.util.TestUtil
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TodoDaoTest : DbTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndRead() {
        val todo = TestUtil.createTodo("do something", "2018-01-01", 0)
        val id = db.todoDao().insert(todo)
        val loaded = LiveDataTestUtil.getValue(db.todoDao().find(id))
        assertThat(loaded, notNullValue())
        assertThat(loaded.task, `is`("do something"))
        assertThat(loaded.date, `is`("2018-01-01"))
        assertThat(loaded.status, `is`(0))
    }

    @Test
    fun insertAndUpdate() {
        val todo = TestUtil.createTodo("do something", "2018-01-01", 0)
        val id = db.todoDao().insert(todo)

        //update task
        todo.id = id
        todo.task = "do something else"
        todo.status = 1
        db.todoDao().update(todo)

        val loaded = getValue(db.todoDao().find(id))
        assertThat(loaded, notNullValue())
        assertThat(loaded.task, `is`("do something else"))
        assertThat(loaded.status, `is`(1))
    }

    @Test
    fun insertAndReadByDate() {
        val todos = TestUtil.createRandomTodoByDate(10, listOf("2018-01-01", "2018-02-02"))
        for (todo: Todo in todos) {
            db.todoDao().insert(todo)
        }
        val todoByDate = getValue(db.todoDao().findByDate("2018-02-02"))
        for (todo: Todo in todoByDate) {
            assertThat(todo.date, `is`("2018-02-02"))
        }
    }

    @Test
    fun insertAndDelete() {
        val todo = TestUtil.createTodo("do something", "2018-01-01", 0)
        val id = db.todoDao().insert(todo)
        todo.id = id
        db.todoDao().delete(todo)
        val loaded = getValue(db.todoDao().find(id))
        assertThat(loaded, nullValue())
    }

}