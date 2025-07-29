package hongsunghwi.todotaskscreening.data.model

import java.time.LocalDateTime

data class EncryptedTodo(
    val id: Int,
    val encryptedContent: ByteArray,
    val regDateTime: LocalDateTime,
    val completeDateTime: LocalDateTime?,
    val iv: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EncryptedTodo

        if (id != other.id) return false
        if (!encryptedContent.contentEquals(other.encryptedContent)) return false
        if (regDateTime != other.regDateTime) return false
        if (completeDateTime != other.completeDateTime) return false
        if (!iv.contentEquals(other.iv)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + encryptedContent.contentHashCode()
        result = 31 * result + regDateTime.hashCode()
        result = 31 * result + (completeDateTime?.hashCode() ?: 0)
        result = 31 * result + iv.contentHashCode()
        return result
    }
}
