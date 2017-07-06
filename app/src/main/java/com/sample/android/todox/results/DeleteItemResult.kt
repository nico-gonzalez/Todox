package com.sample.android.todox.results


data class DeleteItemResult(val inProgress: Boolean = false,
    val errorMessage: String? = null,
    val success: Boolean = false,
    val position: Int = -1) {

  companion object Factory {
    fun progress(position: Int): DeleteItemResult {
      return DeleteItemResult(inProgress = true, position = position)
    }

    fun failure(errorMessage: String?): DeleteItemResult {
      return DeleteItemResult(inProgress = false, errorMessage = errorMessage, success = false)
    }

    fun success(): DeleteItemResult {
      return DeleteItemResult(inProgress = false, success = true)
    }
  }
}
