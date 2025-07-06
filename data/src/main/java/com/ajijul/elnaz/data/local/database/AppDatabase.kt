package com.ajijul.elnaz.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ajijul.elnaz.data.local.dao.ItemDao
import com.ajijul.elnaz.data.local.entity.ItemEntity

@Database(entities = [ItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}