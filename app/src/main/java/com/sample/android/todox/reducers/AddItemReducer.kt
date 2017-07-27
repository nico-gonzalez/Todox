package com.sample.android.todox.reducers

import com.sample.android.todox.common.SchedulerProvider
import com.sample.android.todox.common.UIEvent.AddItemUIEvent
import com.sample.android.todox.results.AddItemResult
import com.sample.android.todox.stores.items.ItemsStore
import io.reactivex.Flowable

class AddItemReducer(val schedulers: SchedulerProvider,
    val itemsStore: ItemsStore) : Reducer<AddItemUIEvent, AddItemResult> {

  override fun reduce(action: AddItemUIEvent): Flowable<AddItemResult> = itemsStore.dispatch(action)
      .map { AddItemResult.success() }
      .onErrorReturn { error -> AddItemResult.failure(error.message) }
      .subscribeOn(schedulers.io())
      .observeOn(schedulers.ui())
      .startWith(AddItemResult.progress())
}