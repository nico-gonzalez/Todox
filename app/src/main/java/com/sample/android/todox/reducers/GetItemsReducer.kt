package com.sample.android.todox.reducers

import com.sample.android.todox.common.schedulers.SchedulerProvider
import com.sample.android.todox.common.ui.UIEvent.GetItemsUIEvent
import com.sample.android.todox.results.GetItemsResult
import com.sample.android.todox.stores.items.ItemsStore
import io.reactivex.Flowable
import javax.inject.Inject

class GetItemsReducer @Inject constructor(private val schedulers: SchedulerProvider,
    private val itemsStore: ItemsStore) : Reducer<GetItemsUIEvent, GetItemsResult> {

  override fun reduce(action: GetItemsUIEvent): Flowable<GetItemsResult> = itemsStore.getState()
      .map { items -> GetItemsResult.success(items) }
      .onErrorReturn { error -> GetItemsResult.failure(error.message) }
      .subscribeOn(schedulers.io())
      .observeOn(schedulers.ui())
      .startWith(GetItemsResult.progress())
}