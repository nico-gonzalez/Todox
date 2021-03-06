package com.sample.android.todox.common.arch.reducers

import com.sample.android.todox.common.schedulers.SchedulerProvider
import com.sample.android.todox.common.ui.UIEvent.AddItemUIEvent
import com.sample.android.todox.common.arch.results.AddItemResult
import com.sample.android.todox.common.arch.stores.items.ItemsStore
import io.reactivex.Flowable
import javax.inject.Inject

class AddItemReducer @Inject constructor(private val schedulers: SchedulerProvider,
    private val itemsStore: ItemsStore) : Reducer<AddItemUIEvent, AddItemResult> {

  override fun reduce(action: AddItemUIEvent): Flowable<AddItemResult> = itemsStore.dispatch(action)
      .map { AddItemResult.success() }
      .onErrorReturn { error -> AddItemResult.failure(error.message) }
      .subscribeOn(schedulers.io())
      .observeOn(schedulers.ui())
      .startWith(AddItemResult.progress())
}