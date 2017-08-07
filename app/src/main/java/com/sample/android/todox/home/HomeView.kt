package com.sample.android.todox.home

import com.sample.android.todox.common.UIModel.GetItemsUIModel

interface HomeView {

  fun showProgress()

  fun hideProgress()

  fun showItems(getItemsUIModel: GetItemsUIModel)

  fun showErrorMessage(errorMessage: String?)

  fun showAddItem()
}