package hongsunghwi.todotaskscreening.crypto.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hongsunghwi.todotaskscreening.crypto.KeystoreManager

@Module
@InstallIn(SingletonComponent::class)
object KeystoreModule {

    @Provides
    fun providesKeystoreManger() = KeystoreManager()
}