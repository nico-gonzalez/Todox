package com.sample.android.todox.additem

import com.sample.android.todox.common.arch.presentation.Presenter
import com.sample.android.todox.common.ui.UIEvent.AddItemUIEvent
import com.sample.android.todox.common.di.FragmentScope
import com.sample.android.todox.common.arch.reducers.AddItemReducer
import com.sample.android.todox.common.arch.reducers.Reducer
import com.sample.android.todox.common.arch.results.AddItemResult
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