package com.sample.android.todox.additem

import com.sample.android.todox.common.architecture.Presenter
import com.sample.android.todox.common.ui.UIEvent.AddItemUIEvent
import com.sample.android.todox.common.di.FragmentScope
import com.sample.android.todox.reducers.AddItemReducer
import com.sample.android.todox.reducers.Reducer
import com.sample.android.todox.results.AddItemResult
import dagger.Binds
import dagger.Module
import dagger.Subcomponent

@FragmentScope
@Module
abstract class AddItemModule {
  @FragmentScope
  @Binds
  abstract fun bindAddItemPresenter(addItemPresenter: AddItemPresenter): Presenter<AddItemView>

  @FragmentScope
  @Binds
  abstract fun bindAddItemReducer(
      addItemReducer: AddItemReducer): Reducer<AddItemUIEvent, AddItemResult>
}

@FragmentScope
@Subcomponent(modules = arrayOf(AddItemModule::class))
interface AddItemSubcomponent {
  fun inject(addItemFragment: AddItemFragment)
}