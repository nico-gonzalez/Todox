package com.sample.android.todox.common.di

import android.content.Context
import com.sample.android.todox.common.RxJavaSchedulerProvider
import com.sample.android.todox.common.SchedulerProvider
import com.sample.android.todox.reducers.GetItemsReducer
import items.ItemsStore

class DependencyInjector(val context: Context) : Injector {

    private var itemsStore: ItemsStore? = null

    private var itemsReducer: GetItemsReducer? = null

    override fun provideSchedulerProvider(): SchedulerProvider = RxJavaSchedulerProvider()

    override fun provideItemsStore(): ItemsStore {
        if (itemsStore == null) {
            itemsStore = ItemsStore()
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

}