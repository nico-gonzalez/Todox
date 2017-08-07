package com.sample.android.todox.home

import com.sample.android.todox.common.architecture.Presenter
import com.sample.android.todox.common.ui.UIEvent.DeleteItemUIEvent
import com.sample.android.todox.common.ui.UIEvent.GetItemsUIEvent
import com.sample.android.todox.common.ui.UIModel.GetItemsUIModel
import com.sample.android.todox.reducers.DeleteItemReducer
import com.sample.android.todox.reducers.GetItemsReducer
import com.sample.android.todox.results.DeleteItemResult
import com.sample.android.todox.results.GetItemsResult
import com.sample.android.todox.stores.items.Item
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

    addDisposable(getItemsReducer.reduce(GetItemsUIEvent())
        .subscribe(::handleGetItemsResult)
    )
  }

  fun deleteItem(item: Item) {

    fun handleDeleteItemResult(result: DeleteItemResult) {
      when {
        !result.inProgress -> when {
          !result.success -> getView()?.showErrorMessage(result.errorMessage)
        }
      }
    }

    addDisposable(
        deleteItemReducer.reduce(DeleteItemUIEvent(item))
            .subscribe(::handleDeleteItemResult)
    )
  }

  fun onAddItem() {
    getView()?.showAddItem()
  }
}