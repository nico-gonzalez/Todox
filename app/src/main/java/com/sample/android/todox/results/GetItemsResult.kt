package com.sample.android.todox.results

import com.sample.android.todox.stores.items.Item

data class GetItemsResult(val inProgress: Boolean = false,
                          val errorMessage: String? = null,
                          val success: Boolean = false,
                          val items: MutableList<Item> = arrayListOf()) {

    companion object Factory {
        fun progress(): GetItemsResult {
            return GetItemsResult(inProgress = true)
        }

        fun failure(errorMessage: String?): GetItemsResult {
            return GetItemsResult(inProgress = false, errorMessage = errorMessage, success = false)
        }

        fun success(items: MutableList<Item>): GetItemsResult {
            return GetItemsResult(inProgress = false, items = items, success = true)
        }
    }
}