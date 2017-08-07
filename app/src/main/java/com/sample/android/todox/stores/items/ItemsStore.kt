package com.sample.android.todox.stores.items

import com.sample.android.todox.common.UIEvent
import com.sample.android.todox.common.UIEvent.AddItemUIEvent
import com.sample.android.todox.common.UIEvent.DeleteItemUIEvent
import com.sample.android.todox.common.UIEvent.GetItemsUIEvent
import com.sample.android.todox.model.items.ItemDao
import com.sample.android.todox.model.items.ItemModel
import com.sample.android.todox.stores.Store
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

class ItemsStore @Inject constructor(private val itemDao: ItemDao) : Store<List<Item>> {

  override fun getState(): Flowable<List<Item>> = fetchItems()

  override fun dispatch(event: UIEvent): Flowable<out Any> = when (event) {
    is GetItemsUIEvent -> fetchItems()
    is DeleteItemUIEvent -> deleteItem(event.item)
    is AddItemUIEvent -> addItem(event.item)
  }

  private fun addItem(item: Item) = Flowable.just(item)
      .map { ItemModel(it.id, it.title, it.description) }
      .concatMap { Flowable.fromCallable { itemDao.insert(it) } }

  private fun deleteItem(item: Item) = Flowable.just(item)
      .map { (id, title, description) -> ItemModel(id, title, description) }
      .concatMap {
        Flowable.fromCallable { itemDao.delete(it) }
      }

  private fun fetchItems(): Flowable<List<Item>> =
      itemDao.getAll()
          .flatMap {
            Observable.fromIterable(it)
                .map { Item(id = it.id, title = it.title, description = it.description) }
                .toList()
                .toFlowable()
          }
}