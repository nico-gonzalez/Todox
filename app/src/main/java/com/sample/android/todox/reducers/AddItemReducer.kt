package com.sample.android.todox.reducers

import com.sample.android.todox.common.SchedulerProvider
import com.sample.android.todox.common.UIEvent.AddItemUIEvent
import com.sample.android.todox.results.AddItemResult
import io.reactivex.Flowable
import items.ItemsStore

class AddItemReducer(val schedulers: SchedulerProvider,
    val itemsStore: ItemsStore) : Reducer<AddItemUIEvent, AddItemResult> {

  override fun reduce(action: AddItemUIEvent): Flowable<AddItemResult> = itemsStore.dispatch(action)
      .map { AddItemResult.success() }
      .onErrorReturn { error -> AddItemResult.failure(error.message) }
      .subscribeOn(schedulers.io())
      .observeOn(schedulers.ui())
      .startWith(AddItemResult.progress(action.position))
}