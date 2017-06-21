package com.sample.android.todox.common.di

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.sample.android.todox.common.RxJavaSchedulerProvider
import com.sample.android.todox.common.SchedulerProvider
import com.sample.android.todox.model.db.Database
import com.sample.android.todox.model.items.ItemDao
import com.sample.android.todox.reducers.AddItemReducer
import com.sample.android.todox.reducers.DeleteItemReducer
import com.sample.android.todox.reducers.GetItemsReducer
import items.ItemsStore

class DependencyInjector(val context: Context) : Injector {

    private var itemsStore: ItemsStore? = null

    private var itemsReducer: GetItemsReducer? = null

    private var deleteItemReducer: DeleteItemReducer? = null

    private var addItemReducer: AddItemReducer? = null

    private var roomDatabase: RoomDatabase? = null

    override fun provideItemDao(): ItemDao {
        return provideDatabase()
                .itemDao()
    }

    override fun provideDatabase(): Database {
        if (roomDatabase == null) {
            roomDatabase = Room.databaseBuilder(context, Database::class.java, "todox")
                    .build()
        }
        return roomDatabase as Database
    }

    override fun provideSchedulerProvider(): SchedulerProvider = RxJavaSchedulerProvider()

    override fun provideItemsStore(): ItemsStore {
        if (itemsStore == null) {
            itemsStore = ItemsStore(provideItemDao())
        }
        return itemsStore as ItemsStore
    }

    override fun provideGetItemsReducer(): GetItemsReducer {
        if (itemsReducer == null) {
            itemsReducer = GetItemsReducer(provideSchedulerProvider(),
                    provideItemsStore())
        }
        return itemsReducer as GetItemsReducer
    }

    override fun provideDeleteItemReducer(): DeleteItemReducer {
        if (deleteItemReducer == null) {
            deleteItemReducer = DeleteItemReducer(provideSchedulerProvider(), provideItemsStore())
        }

        return deleteItemReducer as DeleteItemReducer
    }

    override fun provideAddItemReducer(): AddItemReducer {
        if (addItemReducer == null) {
            addItemReducer = AddItemReducer(provideSchedulerProvider(), provideItemsStore())
        }

        return addItemReducer as AddItemReducer
    }
}