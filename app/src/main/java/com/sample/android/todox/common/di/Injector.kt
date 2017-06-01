package com.sample.android.todox.common.di

import com.sample.android.todox.common.SchedulerProvider
import com.sample.android.todox.reducers.GetItemsReducer
import items.ItemsStore

interface Injector {

    fun provideSchedulerProvider(): SchedulerProvider

    fun provideItemsStore(): ItemsStore

    fun provideGetItemsReducer(): GetItemsReducer
}