package com.sample.android.todox.common.arch.presentation

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class Presenter<T> {

  protected val disposables: CompositeDisposable = CompositeDisposable()

  private var view: T? = null

  open fun attachView(view: T) {
    this.view = view
  }

  open fun detachView() {
    view = null
    disposables.clear()
  }

  protected fun getView(): T? {
    return view
  }

  protected fun addDisposable(disposable: Disposable) {
    disposables.add(disposable)
  }
}