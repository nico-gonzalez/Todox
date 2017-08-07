package com.sample.android.todox.home

import com.sample.android.todox.common.arch.presentation.Presenter
import com.sample.android.todox.common.ui.UIEvent.DeleteItemUIEvent
import com.sample.android.todox.common.ui.UIEvent.GetItemsUIEvent
import com.sample.android.todox.common.di.ActivityScope
import com.sample.android.todox.common.arch.reducers.DeleteItemReducer
import com.sample.android.todox.common.arch.reducers.GetItemsReducer
import com.sample.android.todox.common.arch.reducers.Reducer
import com.sample.android.todox.common.arch.results.DeleteItemResult
import com.sample.android.todox.common.arch.results.GetItemsResult
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