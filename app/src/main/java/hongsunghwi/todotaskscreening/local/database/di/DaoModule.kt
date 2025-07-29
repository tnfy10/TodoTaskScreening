package hongsunghwi.todotaskscreening.local.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hongsunghwi.todotaskscreening.local.database.AppDatabase

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun provideTodoDao(
        appDatabase: AppDatabase
    ) = appDatabase.todoDao()
}