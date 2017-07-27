package com.sample.android.todox.home

import com.sample.android.todox.common.Presenter
import com.sample.android.todox.common.UIEvent.DeleteItemUIEvent
import com.sample.android.todox.common.UIEvent.GetItemsUIEvent
import com.sample.android.todox.common.UIModel.GetItemsUIModel
import com.sample.android.todox.reducers.DeleteItemReducer
import com.sample.android.todox.reducers.GetItemsReducer
import com.sample.android.todox.results.DeleteItemResult
import com.sample.android.todox.results.GetItemsResult
import com.sample.android.todox.stores.items.Item

class HomePresenter(val getItemsReducer: GetItemsReducer,
    val deleteItemReducer: DeleteItemReducer) : Presenter<HomeView>() {

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

    addDisposable(getItemsReducer.reduce(GetItemsUIEvent())
        .subscribe(::handleGetItemsResult)
    )
  }

  fun deleteItem(position: Int, item: Item) {

    fun handleDeleteItemResult(result: DeleteItemResult) {
      when {
        !result.inProgress -> when {
          !result.success -> getView()?.showErrorMessage(result.errorMessage)
        }
      }
    }

    addDisposable(
        deleteItemReducer.reduce(DeleteItemUIEvent(position, item))
            .subscribe(::handleDeleteItemResult)
    )
  }

  fun onAddItem() {
    getView()?.showAddItem()
  }
}