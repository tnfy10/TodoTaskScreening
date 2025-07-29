package hongsunghwi.todotaskscreening.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "todo"
)
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val content: ByteArray,
    val regTimestamp: Long,
    val completeTimestamp: Long? = null,
    val iv: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TodoEntity

        if (id != other.id) return false
        if (regTimestamp != other.regTimestamp) return false
        if (completeTimestamp != other.completeTimestamp) return false
        if (!content.contentEquals(other.content)) return false
        if (!iv.contentEquals(other.iv)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + regTimestamp.hashCode()
        result = 31 * result + (completeTimestamp?.hashCode() ?: 0)
        result = 31 * result + content.contentHashCode()
        result = 31 * result + iv.contentHashCode()
        return result
    }
}
