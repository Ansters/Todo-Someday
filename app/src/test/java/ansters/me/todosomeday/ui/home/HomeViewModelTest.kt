package ansters.me.todosomeday.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import ansters.me.todosomeday.repository.TodoRepository
import org.mockito.Mockito.mock
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HomeViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository = mock(TodoRepository::class.java)
    private val homeViewModel = HomeViewModel(repository)

}