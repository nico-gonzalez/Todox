package com.sample.android.todox.reducers

import com.sample.android.todox.common.SchedulerProvider
import com.sample.android.todox.home.GetItemsUIEvent
import com.sample.android.todox.results.GetItemsResult
import io.reactivex.Flowable
import items.ItemsStore

class GetItemsReducer(val schedulers: SchedulerProvider,
                      val itemsStore: ItemsStore) : Reducer<GetItemsUIEvent, GetItemsResult> {

    override fun reduce(action: GetItemsUIEvent): Flowable<GetItemsResult> = itemsStore.getState()
            .map { items -> GetItemsResult.success(items) }
            .onErrorReturn { error -> GetItemsResult.failure(error.message) }
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .startWith(GetItemsResult.progress())
}