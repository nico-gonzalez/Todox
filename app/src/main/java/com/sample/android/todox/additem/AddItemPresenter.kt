package com.sample.android.todox.additem

import com.sample.android.todox.common.arch.presentation.Presenter
import com.sample.android.todox.common.ui.UIEvent.AddItemUIEvent
import com.sample.android.todox.common.arch.reducers.AddItemReducer
import com.sample.android.todox.common.arch.results.AddItemResult
import com.sample.android.todox.common.arch.stores.items.Item
import javax.inject.Inject

class AddItemPresenter @Inject constructor(
    private val addItemReducer: AddItemReducer) : Presenter<AddItemView>() {

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