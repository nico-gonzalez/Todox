package com.sample.android.todox.reducers

import com.sample.android.todox.common.SchedulerProvider
import com.sample.android.todox.home.DeleteItemUIEvent
import com.sample.android.todox.results.DeleteItemResult
import io.reactivex.Flowable
import items.ItemsStore

class DeleteItemReducer(val schedulers: SchedulerProvider,
    val itemsStore: ItemsStore) : Reducer<DeleteItemUIEvent, DeleteItemResult> {

  override fun reduce(action: DeleteItemUIEvent): Flowable<DeleteItemResult> = itemsStore.dispatch(
      action)
      .map { DeleteItemResult.success() }
      .onErrorReturn { error -> DeleteItemResult.failure(error.message) }
      .subscribeOn(schedulers.io())
      .observeOn(schedulers.ui())
      .startWith(DeleteItemResult.progress(action.position))
}