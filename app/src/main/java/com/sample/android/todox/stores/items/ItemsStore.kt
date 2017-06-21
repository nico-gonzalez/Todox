package items

import com.sample.android.todox.common.UIEvent
import com.sample.android.todox.home.AddItemUIEvent
import com.sample.android.todox.home.DeleteItemUIEvent
import com.sample.android.todox.home.GetItemsUIEvent
import com.sample.android.todox.model.items.ItemDao
import com.sample.android.todox.model.items.ItemModel
import com.sample.android.todox.stores.Store
import com.sample.android.todox.stores.items.Item
import io.reactivex.Flowable

class ItemsStore(val itemDao: ItemDao) : Store<List<Item>> {

    override fun getState(): Flowable<List<Item>> {
        return getItems()
    }

    override fun dispatch(event: UIEvent): Flowable<List<Item>> = when (event) {
        is GetItemsUIEvent -> getItems()
        is DeleteItemUIEvent -> deleteItem(event.item)
        is AddItemUIEvent -> addItem(event.item)
        else -> Flowable.error(IllegalStateException("Invalid UIEvent"))
    }

    private fun addItem(item: Item): Flowable<List<Item>> = Flowable.just(item)
            .map { ItemModel(it.id, it.title, it.description) }
            .concatMap { Flowable.fromCallable { itemDao.insert(it) } }
            .concatMap { getItems() }

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

    private fun getItems(): Flowable<List<Item>> = itemDao.getAll().take(1)
            .flatMapIterable { it -> it }
            .map { Item(id = it.id, title = it.title, description = it.description) }
            .toList()
            .toFlowable()

}