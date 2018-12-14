package ansters.me.todosomeday.di.Module

import android.app.Application
import androidx.room.Room
import ansters.me.todosomeday.db.TodoDao
import ansters.me.todosomeday.db.TodoDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideDb(app: Application): TodoDb {
        return Room
            .databaseBuilder(app, TodoDb::class.java, "todo.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTodoDao(db: TodoDb): TodoDao {
        return db.todoDao()
    }

}