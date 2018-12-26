package ansters.me.todosomeday.util

import org.junit.Test
import com.google.common.truth.Truth.assertThat
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.text.SimpleDateFormat
import java.util.*

@RunWith(JUnit4::class)
class UtilTest {

    @Test
    fun getDateToday() {
        val formatter = SimpleDateFormat("yyy-MM-dd")
        val currentDate = formatter.format(Date())
        assertThat(currentDate).isEqualTo("2018-12-26")
    }
}