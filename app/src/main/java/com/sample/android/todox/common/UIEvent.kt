package com.sample.android.todox.common

import com.sample.android.todox.stores.items.Item

sealed class UIEvent {
  class GetItemsUIEvent : UIEvent()

  class DeleteItemUIEvent(val position: Int, val item: Item) : UIEvent()

  class AddItemUIEvent(val item: Item) : UIEvent()
}