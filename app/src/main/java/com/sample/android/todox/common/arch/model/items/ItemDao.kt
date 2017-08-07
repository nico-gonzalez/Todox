package com.sample.android.todox.common.arch.model.items

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface ItemDao {

  @Query("SELECT * from items")
  fun getAll(): Flowable<List<ItemModel>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(item: ItemModel): Long

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(items: Array<ItemModel>): Array<Long>

  @Delete
  fun delete(item: ItemModel)

  @Delete
  fun delete(items: Array<ItemModel>)

  @Query("DELETE from items")
  fun truncate()
}