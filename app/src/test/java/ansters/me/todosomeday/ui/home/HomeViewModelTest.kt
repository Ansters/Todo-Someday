package ansters.me.todosomeday.ui.home

import ansters.me.todosomeday.repository.TodoRepository
import org.mockito.Mockito.mock
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HomeViewModelTest {

    private val repository = mock(TodoRepository::class.java)
    private val homeViewModel = HomeViewModel(repository)

}