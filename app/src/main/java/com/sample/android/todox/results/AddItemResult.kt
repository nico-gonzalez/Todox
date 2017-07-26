package com.sample.android.todox.results


data class AddItemResult(val inProgress: Boolean = false,
    val errorMessage: String? = null,
    val success: Boolean = false,
    val position: Int = -1) {

  companion object Factory {
    fun progress(position: Int): AddItemResult {
      return AddItemResult(inProgress = true, position = position)
    }

    fun failure(errorMessage: String?): AddItemResult {
      return AddItemResult(inProgress = false, errorMessage = errorMessage, success = false)
    }

    fun success(): AddItemResult {
      return AddItemResult(inProgress = false, success = true)
    }
  }
}
