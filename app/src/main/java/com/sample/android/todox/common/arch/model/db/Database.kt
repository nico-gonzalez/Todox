package com.sample.android.todox.common.arch.model.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.sample.android.todox.common.arch.model.items.ItemDao
import com.sample.android.todox.common.arch.model.items.ItemModel

@Database(entities = arrayOf(ItemModel::class), version = 1)
abstract class Database : RoomDatabase() {

  abstract fun itemDao(): ItemDao
}