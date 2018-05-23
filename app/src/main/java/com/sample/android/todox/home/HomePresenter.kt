package com.sample.android.todox.home

import com.sample.android.todox.common.addTo
import com.sample.android.todox.common.arch.presentation.Presenter
import com.sample.android.todox.common.arch.reducers.DeleteItemReducer
import com.sample.android.todox.common.arch.reducers.GetItemsReducer
import com.sample.android.todox.common.arch.results.DeleteItemResult
import com.sample.android.todox.common.arch.results.GetItemsResult
import com.sample.android.todox.common.arch.stores.items.Item
import com.sample.android.todox.common.ui.UIEvent.DeleteItemUIEvent
import com.sample.android.todox.common.ui.UIEvent.GetItemsUIEvent
import com.sample.android.todox.common.ui.UIModel.GetItemsUIModel
import javax.inject.Inject

class HomePresenter @Inject constructor(private val getItemsReducer: GetItemsReducer,
    private val deleteItemReducer: DeleteItemReducer) : Presenter<HomeView>() {

  fun onGetItems() {

    fun handleGetItemsResult(result: GetItemsResult) {
      when {
        result.inProgress -> getView()?.showProgress()
        else -> {
          getView()?.hideProgress()
          when {
            !result.success -> getView()?.showErrorMessage(result.errorMessage)
            else -> getView()?.showItems(GetItemsUIModel(items = result.items))
          }
        }
      }
    }

    getItemsReducer.reduce(GetItemsUIEvent())
        .doOnNext { handleGetItemsResult(it) }
        .subscribe()
        .addTo(disposables)
  }

  fun deleteItem(item: Item) {

    fun handleDeleteItemResult(result: DeleteItemResult) {
      when {
        !result.inProgress -> when {
          !result.success -> getView()?.showErrorMessage(result.errorMessage)
        }
      }
    }

    deleteItemReducer.reduce(DeleteItemUIEvent(item))
        .doOnNext { handleDeleteItemResult(it) }
        .subscribe()
        .addTo(disposables)
  }

  fun onAddItem() {
    getView()?.showAddItem()
  }
}