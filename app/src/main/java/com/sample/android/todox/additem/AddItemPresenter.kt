package com.sample.android.todox.additem

import com.sample.android.todox.common.Presenter
import com.sample.android.todox.common.UIEvent.AddItemUIEvent
import com.sample.android.todox.reducers.AddItemReducer
import com.sample.android.todox.results.AddItemResult
import com.sample.android.todox.stores.items.Item

class AddItemPresenter(val addItemReducer: AddItemReducer) : Presenter<AddItemView>() {

  fun onAddItem(title: String, description: String) {

    fun handleAddItemResult(result: AddItemResult) {
      when {
        result.inProgress -> getView()?.showAddSuccess()
        else -> when {
          !result.success -> getView()?.showErrorMessage(result.errorMessage)
        }
      }
    }
    addDisposable(
        addItemReducer.reduce(AddItemUIEvent(Item(title = title, description = description)))
            .doOnError { getView()?.showErrorMessage(it.message) }
            .subscribe(::handleAddItemResult))
  }
}