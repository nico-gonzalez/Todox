package items

import com.sample.android.todox.stores.Store
import com.sample.android.todox.stores.items.Item
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class ItemsStore : Store<MutableList<Item>> {

    private var items: Observable<MutableList<Item>>? = null

    override fun getState(): Observable<MutableList<Item>> {
        items = Observable.just(mutableListOf(Item(1, "test1", "This is item 1"),
                Item(2, "test2", "This is item 2"),
                Item(3, "test3", "This is item 3"),
                Item(4, "test4", "This is item 4"),
                Item(5, "test5", "This is item 5"),
                Item(6, "test6", "This is item 6"),
                Item(7, "test7", "This is item 7"),
                Item(8, "test8", "This is item 8"),
                Item(9, "test9", "This is item 9"),
                Item(10, "test10", "This is item 10"),
                Item(11, "test11", "This is item 11"),
                Item(12, "test12", "This is item 12"),
                Item(13, "test13", "This is item 13"),
                Item(14, "test14", "This is item 14"),
                Item(15, "test15", "This is item 15")
        ))
                .delay(1, TimeUnit.SECONDS)
                .cache()

        return items as Observable<MutableList<Item>>
    }
}