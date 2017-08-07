package com.sample.android.todox.common.arch.results


data class DeleteItemResult(val inProgress: Boolean = false,
    val errorMessage: String? = null,
    val success: Boolean = false) {

  companion object Factory {
    fun progress(): DeleteItemResult {
      return DeleteItemResult(inProgress = true)
    }

    fun failure(errorMessage: String?): DeleteItemResult {
      return DeleteItemResult(inProgress = false, errorMessage = errorMessage, success = false)
    }

    fun success(): DeleteItemResult {
      return DeleteItemResult(inProgress = false, success = true)
    }
  }
}
