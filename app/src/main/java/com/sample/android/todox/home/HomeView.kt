package com.sample.android.todox.home

import com.sample.android.todox.common.UIModel.GetItemsUIModel
import com.sample.android.todox.stores.items.Item

interface HomeView {

  fun showProgress()

  fun hideProgress()

  fun showItems(getItemsUIModel: GetItemsUIModel)

  fun showErrorMessage(errorMessage: String?)

  fun showAddItem()
}