package ansters.me.todosomeday.di

import android.app.Application
import ansters.me.todosomeday.TodoApp
import ansters.me.todosomeday.di.Module.ActivityModule
import ansters.me.todosomeday.di.Module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        AppModule::class
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