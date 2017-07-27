package com.sample.android.todox.common

import com.sample.android.todox.stores.items.Item

sealed class UIModel {
  class GetItemsUIModel(val items: List<Item>) : UIModel()

}