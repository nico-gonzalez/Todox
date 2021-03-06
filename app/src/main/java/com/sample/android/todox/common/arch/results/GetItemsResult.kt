package com.sample.android.todox.common.arch.results

import com.sample.android.todox.common.arch.stores.items.Item

data class GetItemsResult(val inProgress: Boolean = false,
    val errorMessage: String? = null,
    val success: Boolean = false,
    val items: List<Item> = arrayListOf()) {

  companion object Factory {
    fun progress(): GetItemsResult {
      return GetItemsResult(inProgress = true)
    }

    fun failure(errorMessage: String?): GetItemsResult {
      return GetItemsResult(inProgress = false, errorMessage = errorMessage, success = false)
    }

    fun success(items: List<Item>): GetItemsResult {
      return GetItemsResult(inProgress = false, items = items, success = true)
    }
  }
}