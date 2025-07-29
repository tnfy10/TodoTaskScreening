package hongsunghwi.todotaskscreening.crypto

import java.security.Key
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec

class AES256Encryption {

    private val algorithm = "AES"
    private val transformation = "AES/CBC/PKCS7Padding"
    private val keySize = 256

    fun encrypt(data: ByteArray, key: Key): Pair<ByteArray, ByteArray> {
        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val encryptedData = cipher.doFinal(data)

        return Pair(encryptedData, cipher.iv)
    }

    fun decrypt(encryptedData: ByteArray, iv: ByteArray, key: Key): ByteArray {
        val ivParameterSpec = IvParameterSpec(iv)
        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec)
        return cipher.doFinal(encryptedData)
    }
}