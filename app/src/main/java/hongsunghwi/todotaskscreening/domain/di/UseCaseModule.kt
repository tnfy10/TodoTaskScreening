package hongsunghwi.todotaskscreening.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import hongsunghwi.todotaskscreening.crypto.AES256Encryption
import hongsunghwi.todotaskscreening.crypto.KeystoreManager
import hongsunghwi.todotaskscreening.domain.repository.TodoRepository
import hongsunghwi.todotaskscreening.domain.usecase.GetTodosUseCase

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun providesGetTodosUseCase(
        todoRepository: TodoRepository,
        keystoreManager: KeystoreManager,
        aeS256Encryption: AES256Encryption
    ) = GetTodosUseCase(todoRepository, keystoreManager, aeS256Encryption)
}