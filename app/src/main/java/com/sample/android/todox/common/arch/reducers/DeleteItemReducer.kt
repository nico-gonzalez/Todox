package com.sample.android.todox.common.arch.reducers

import com.sample.android.todox.common.schedulers.SchedulerProvider
import com.sample.android.todox.common.ui.UIEvent.DeleteItemUIEvent
import com.sample.android.todox.common.arch.results.DeleteItemResult
import com.sample.android.todox.common.arch.stores.items.ItemsStore
import io.reactivex.Flowable
import javax.inject.Inject

class DeleteItemReducer @Inject constructor(private val schedulers: SchedulerProvider,
    private val itemsStore: ItemsStore) : Reducer<DeleteItemUIEvent, DeleteItemResult> {

  override fun reduce(action: DeleteItemUIEvent): Flowable<DeleteItemResult> = itemsStore.dispatch(
      action)
      .map { DeleteItemResult.success() }
      .onErrorReturn { error -> DeleteItemResult.failure(error.message) }
      .subscribeOn(schedulers.io())
      .observeOn(schedulers.ui())
      .startWith(DeleteItemResult.progress())
}