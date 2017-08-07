package com.sample.android.todox.common.di

import android.arch.persistence.room.Room
import android.content.Context
import com.sample.android.todox.additem.AddItemSubcomponent
import com.sample.android.todox.common.RxJavaSchedulerProvider
import com.sample.android.todox.common.SchedulerProvider
import com.sample.android.todox.home.HomeSubcomponent
import com.sample.android.todox.model.db.Database
import com.sample.android.todox.model.items.ItemDao
import com.sample.android.todox.stores.Store
import com.sample.android.todox.stores.items.Item
import com.sample.android.todox.stores.items.ItemsStore
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
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
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

  fun context(): Context

  fun schedulerProvider(): SchedulerProvider

  fun itemsStore(): Store<List<Item>>

  fun homeSubcomponent(): HomeSubcomponent

  fun addItemSubcomponent(): AddItemSubcomponent
}