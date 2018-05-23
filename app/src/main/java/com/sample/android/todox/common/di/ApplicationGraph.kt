package com.sample.android.todox.common.di

import android.arch.persistence.room.Room
import android.content.Context
import com.sample.android.todox.additem.AddItemSubcomponent
import com.sample.android.todox.common.schedulers.RxJavaSchedulerProvider
import com.sample.android.todox.common.schedulers.SchedulerProvider
import com.sample.android.todox.home.HomeSubcomponent
import com.sample.android.todox.common.arch.model.db.Database
import com.sample.android.todox.common.arch.model.items.ItemDao
import com.sample.android.todox.common.arch.stores.Store
import com.sample.android.todox.common.arch.stores.items.Item
import com.sample.android.todox.common.arch.stores.items.ItemsStore
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context) {

  @Singleton
  @Provides
  fun provideContext(): Context = context

  @Singleton
  @Provides
  fun provideSchedulerProvider(): SchedulerProvider = RxJavaSchedulerProvider()

  @Singleton
  @Provides
  fun provideDatabase(context: Context): Database = Room.databaseBuilder(
      context,
      Database::class.java, "todox")
      .build()

  @Singleton
  @Provides
  fun provideItemDao(database: Database): ItemDao = database.itemDao()

  @Singleton
  @Provides
  fun provideItemsStore(itemDao: ItemDao): Store<List<Item>> = ItemsStore(itemDao)

}

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

  fun context(): Context

  fun schedulerProvider(): SchedulerProvider

  fun itemsStore(): Store<List<Item>>

  fun homeSubcomponent(): HomeSubcomponent

  fun addItemSubcomponent(): AddItemSubcomponent
}