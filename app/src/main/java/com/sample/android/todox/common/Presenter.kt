package com.sample.android.todox.common

abstract class Presenter<T> {

    private var view: T? = null

    open fun attachView(view: T) {
        this.view = view
    }

    open fun detachView() {
        view = null
    }

    protected fun getView() : T? {
        return view
    }
}