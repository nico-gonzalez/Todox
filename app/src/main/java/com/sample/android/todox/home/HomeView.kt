package com.sample.android.todox.home

interface HomeView {

    fun showProgress()

    fun hideProgress()

    fun showItems(getItemsUIModel: GetItemsUIModel)

    fun showErrorMessage(errorMessage: String?)
}