package ansters.me.todosomeday.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ansters.me.todosomeday.di.DaggerViewModelFactory
import ansters.me.todosomeday.di.ViewModelKey
import ansters.me.todosomeday.ui.home.HomeViewModel
import ansters.me.todosomeday.ui.todo.TodoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TodoViewModel::class)
    abstract fun bindUserViewModel(todoViewModel: TodoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

}