package items

import com.sample.android.todox.common.UIEvent
import com.sample.android.todox.common.UIEvent.AddItemUIEvent
import com.sample.android.todox.common.UIEvent.DeleteItemUIEvent
import com.sample.android.todox.common.UIEvent.GetItemsUIEvent
import com.sample.android.todox.model.items.ItemDao
import com.sample.android.todox.model.items.ItemModel
import com.sample.android.todox.stores.Store
import io.reactivex.BackpressureStrategy.BUFFER
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject

class ItemsStore(val itemDao: ItemDao) : Store<List<Item>> {

  var items: Flowable<List<Item>>? = null

  var subject: BehaviorSubject<List<Item>> = BehaviorSubject.create()

  override fun getState(): Flowable<List<Item>> = subject.toFlowable(BUFFER)
      .startWith(fetchItems())

  override fun dispatch(event: UIEvent): Flowable<List<Item>> = when (event) {
    is GetItemsUIEvent -> fetchItems()
    is DeleteItemUIEvent -> deleteItem(event.item)
    is AddItemUIEvent -> addItem(event.item)
  }

  private fun addItem(item: Item): Flowable<List<Item>> = Flowable.just(item)
      .map { ItemModel(it.id, it.title, it.description) }
      .concatMap { Flowable.fromCallable { itemDao.insert(it) } }
      .concatMap { fetchItems() }
      .doOnNext { subject.onNext(it) }

  private fun deleteItem(item: Item): Flowable<List<Item>> = Flowable.just(item)
      .map { (id, title, description) -> ItemModel(id, title, description) }
      .concatMap {
        Flowable.fromCallable { itemDao.delete(it) }
            .flatMap { _ -> itemDao.getAll().take(1) }
            .flatMapIterable { it -> it }
            .map { Item(it.id, it.title, it.description) }
            .toList()
            .toFlowable()
      }
      .doOnNext { subject.onNext(it) }

  private fun fetchItems(): Flowable<List<Item>> =
      itemDao.getAll().take(1)
          .flatMapIterable { it -> it }
          .map { Item(id = it.id, title = it.title, description = it.description) }
          .toList()
          .toFlowable()
          .doOnNext { subject.onNext(it) }
}