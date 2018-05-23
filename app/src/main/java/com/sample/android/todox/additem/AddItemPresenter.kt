package com.sample.android.todox.additem

import com.sample.android.todox.common.addTo
import com.sample.android.todox.common.arch.presentation.Presenter
import com.sample.android.todox.common.arch.reducers.AddItemReducer
import com.sample.android.todox.common.arch.results.AddItemResult
import com.sample.android.todox.common.arch.stores.items.Item
import com.sample.android.todox.common.ui.UIEvent.AddItemUIEvent
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
    addItemReducer.reduce(AddItemUIEvent(Item(title = title, description = description)))
        .doOnError { getView()?.showErrorMessage(it.message) }
        .doOnNext(::handleAddItemResult)
        .subscribe()
        .addTo(disposables)
  }
}