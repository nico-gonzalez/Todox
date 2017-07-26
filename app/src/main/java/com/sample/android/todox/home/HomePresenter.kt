package com.sample.android.todox.home

import com.sample.android.todox.common.Presenter
import com.sample.android.todox.common.UIEvent
import com.sample.android.todox.common.UIModel
import com.sample.android.todox.reducers.AddItemReducer
import com.sample.android.todox.reducers.DeleteItemReducer
import com.sample.android.todox.reducers.GetItemsReducer
import com.sample.android.todox.results.AddItemResult
import com.sample.android.todox.results.DeleteItemResult
import com.sample.android.todox.results.GetItemsResult
import com.sample.android.todox.stores.items.Item

class GetItemsUIEvent : UIEvent

class DeleteItemUIEvent(val position: Int, val item: Item) : UIEvent

class AddItemUIEvent(val position: Int, val item: Item) : UIEvent

class GetItemsUIModel(val items: List<Item>) : UIModel

class HomePresenter(val getItemsReducer: GetItemsReducer,
    val deleteItemReducer: DeleteItemReducer,
    val addItemReducer: AddItemReducer) : Presenter<HomeView>() {

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
        result.inProgress -> getView()?.deleteItem(result.position)
        else -> when {
          !result.success -> getView()?.showErrorMessage(result.errorMessage)
        }
      }
    }

    addDisposable(
        deleteItemReducer.reduce(DeleteItemUIEvent(position, item))
            .subscribe(::handleDeleteItemResult)
    )
  }

  fun addItem(position: Int, item: Item) {
    fun handleAddItemResult(result: AddItemResult) {
      when {
        result.inProgress -> getView()?.addItem(result.position, item)
        else -> when {
          !result.success -> getView()?.showErrorMessage(result.errorMessage)
        }
      }
    }

    addDisposable(
        addItemReducer.reduce(AddItemUIEvent(position, item))
            .subscribe(::handleAddItemResult)
    )
  }
}