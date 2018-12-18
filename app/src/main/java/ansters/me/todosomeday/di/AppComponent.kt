package ansters.me.todosomeday.di

import android.app.Application
import ansters.me.todosomeday.TodoApp
import ansters.me.todosomeday.di.module.ActivityModule
import ansters.me.todosomeday.di.module.AppModule
import ansters.me.todosomeday.di.module.FragmentModule
import ansters.me.todosomeday.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        AppModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(todoApp: TodoApp)

}