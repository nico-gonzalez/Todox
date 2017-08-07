package com.sample.android.todox.common.ui

import com.sample.android.todox.common.arch.stores.items.Item

sealed class UIModel {
  class GetItemsUIModel(val items: List<Item>) : UIModel()

}