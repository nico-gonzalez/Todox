package com.sample.android.todox.reducers

import com.sample.android.todox.common.SchedulerProvider
import com.sample.android.todox.home.GetItemsUIEvent
import com.sample.android.todox.results.GetItemsResult
import io.reactivex.Observable
import items.ItemsStore

class GetItemsReducer(val schedulers: SchedulerProvider,
                      val itemsStore: ItemsStore) : Reducer<GetItemsUIEvent, GetItemsResult> {

    private val state by lazy {
        itemsStore.getState()
                .map { items -> GetItemsResult.success(items) }
                .onErrorReturn { error -> GetItemsResult.failure(error.message) }
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .startWith(GetItemsResult.progress())
    }

    override fun reduce(action: GetItemsUIEvent): Observable<GetItemsResult> {
        return state
    }
}