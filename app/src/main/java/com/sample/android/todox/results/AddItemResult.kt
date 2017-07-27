package com.sample.android.todox.results


data class AddItemResult(val inProgress: Boolean = false,
    val errorMessage: String? = null,
    val success: Boolean = false) {

  companion object Factory {
    fun progress(): AddItemResult {
      return AddItemResult(inProgress = true)
    }

    fun failure(errorMessage: String?): AddItemResult {
      return AddItemResult(inProgress = false, errorMessage = errorMessage, success = false)
    }

    fun success(): AddItemResult {
      return AddItemResult(inProgress = false, success = true)
    }
  }
}
