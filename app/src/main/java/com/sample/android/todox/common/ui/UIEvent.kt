package com.sample.android.todox.common.ui

import com.sample.android.todox.stores.items.Item

sealed class UIEvent {
  class GetItemsUIEvent : UIEvent()

  class DeleteItemUIEvent(val item: Item) : UIEvent()

  class AddItemUIEvent(val item: Item) : UIEvent()
}