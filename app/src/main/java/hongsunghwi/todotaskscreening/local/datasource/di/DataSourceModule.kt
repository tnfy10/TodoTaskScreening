package hongsunghwi.todotaskscreening.local.datasource.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hongsunghwi.todotaskscreening.data.datasource.TodoDataSource
import hongsunghwi.todotaskscreening.local.datasource.TodoDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {

    @Binds
    abstract fun bindTodoDataSource(
        todoDataSourceImpl: TodoDataSourceImpl
    ): TodoDataSource
}