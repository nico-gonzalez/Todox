package com.sample.android.todox.home

import com.sample.android.todox.stores.items.Item

interface HomeView {

  fun showProgress()

  fun hideProgress()

  fun showItems(getItemsUIModel: GetItemsUIModel)

  fun showErrorMessage(errorMessage: String?)

  fun deleteItem(position: Int)

  fun addItem(position: Int, item: Item)
}