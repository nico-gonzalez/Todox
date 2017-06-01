package com.sample.android.todox.home

import com.sample.android.todox.common.Presenter
import com.sample.android.todox.common.UIEvent
import com.sample.android.todox.common.UIModel
import com.sample.android.todox.reducers.GetItemsReducer
import com.sample.android.todox.stores.items.Item
import io.reactivex.disposables.CompositeDisposable

class GetItemsUIEvent : UIEvent

class GetItemsUIModel(val items: MutableList<Item>) : UIModel

class HomePresenter(val getItemsReducer: GetItemsReducer) : Presenter<HomeView>() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    fun onGetItems() {
        disposables.add(
                getItemsReducer.reduce(GetItemsUIEvent())
                        .subscribe { (inProgress, errorMessage, success, items) ->

                            if (inProgress) {
                                getView()?.showProgress()
                            } else {
                                getView()?.hideProgress()

                                if (!success) {
                                    getView()?.showErrorMessage(errorMessage)
                                } else {
                                    getView()?.showItems(GetItemsUIModel(items = items))
                                }
                            }
                        }
        )
    }

    override fun detachView() {
        super.detachView()
        disposables.dispose()
    }
}