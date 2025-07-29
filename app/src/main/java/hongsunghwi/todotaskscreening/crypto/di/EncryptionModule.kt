package hongsunghwi.todotaskscreening.crypto.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hongsunghwi.todotaskscreening.crypto.AES256Encryption

@Module
@InstallIn(SingletonComponent::class)
object EncryptionModule {

    @Provides
    fun provideAES256Encryption() = AES256Encryption()
}