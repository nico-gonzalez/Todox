package com.sample.android.todox.common.di

import android.arch.persistence.room.RoomDatabase
import com.sample.android.todox.common.SchedulerProvider
import com.sample.android.todox.model.items.ItemDao
import com.sample.android.todox.reducers.AddItemReducer
import com.sample.android.todox.reducers.DeleteItemReducer
import com.sample.android.todox.reducers.GetItemsReducer
import com.sample.android.todox.stores.items.ItemsStore

interface Injector {

  fun provideSchedulerProvider(): SchedulerProvider

  fun provideItemsStore(): ItemsStore

  fun provideGetItemsReducer(): GetItemsReducer

  fun provideDeleteItemReducer(): DeleteItemReducer

  fun provideItemDao(): ItemDao

  fun provideDatabase(): RoomDatabase

  fun provideAddItemReducer(): AddItemReducer
}