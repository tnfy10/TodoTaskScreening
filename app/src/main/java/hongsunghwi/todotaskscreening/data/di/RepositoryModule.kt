package hongsunghwi.todotaskscreening.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hongsunghwi.todotaskscreening.data.repository.TodoRepositoryImpl
import hongsunghwi.todotaskscreening.domain.repository.TodoRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindTodoRepository(
        todoRepositoryImpl: TodoRepositoryImpl
    ): TodoRepository
}