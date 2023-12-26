package com.example.wishlist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Wishlist(
    val receiver: String,
    val wishes: List<String>,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Wishlist

        if (receiver != other.receiver) return false
        if (!wishes.containsAll(other.wishes)) return false
        if (!other.wishes.containsAll(wishes)) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = receiver.hashCode()
        result = 31 * result + id
        return result
    }
}