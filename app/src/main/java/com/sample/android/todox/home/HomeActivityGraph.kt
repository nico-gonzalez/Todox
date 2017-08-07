package com.sample.android.todox.home

import com.sample.android.todox.common.Presenter
import com.sample.android.todox.common.UIEvent.DeleteItemUIEvent
import com.sample.android.todox.common.UIEvent.GetItemsUIEvent
import com.sample.android.todox.common.di.ActivityScope
import com.sample.android.todox.reducers.DeleteItemReducer
import com.sample.android.todox.reducers.GetItemsReducer
import com.sample.android.todox.reducers.Reducer
import com.sample.android.todox.results.DeleteItemResult
import com.sample.android.todox.results.GetItemsResult
import dagger.Binds
import dagger.Module
import dagger.Subcomponent

@Module
abstract class HomeModule {

  @ActivityScope
  @Binds
  abstract fun bindHomePresenter(homePresenter: HomePresenter): Presenter<HomeView>

  @ActivityScope
  @Binds
  abstract fun bindGetItemsReducer(
      getItemsReducer: GetItemsReducer): Reducer<GetItemsUIEvent, GetItemsResult>

  @ActivityScope
  @Binds
  abstract fun bindDeleteItemReducer(
      deleteItemReducer: DeleteItemReducer): Reducer<DeleteItemUIEvent, DeleteItemResult>
}

@ActivityScope
@Subcomponent(modules = arrayOf(HomeModule::class))
interface HomeSubcomponent {
  fun inject(homeActivity: HomeActivity)
}