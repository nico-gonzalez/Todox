package com.sample.android.todox.model.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.sample.android.todox.model.items.ItemModel
import com.sample.android.todox.model.items.ItemDao

@Database(entities = arrayOf(ItemModel::class), version = 1)
abstract class Database : RoomDatabase() {

    abstract fun itemDao(): ItemDao
}