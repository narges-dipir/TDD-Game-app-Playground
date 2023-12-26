package com.example.wishlist.dataPersistance

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.wishlist.model.Wishlist

@Database(entities = [Wishlist::class], version = 1)
@TypeConverters(StringListConverter::class)
abstract class WishlistDatabase : RoomDatabase() {
}
