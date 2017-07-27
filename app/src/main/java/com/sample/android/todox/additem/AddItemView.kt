package com.sample.android.todox.additem

interface AddItemView {
  fun showAddSuccess()

  fun showErrorMessage(errorMessage: String?)
}