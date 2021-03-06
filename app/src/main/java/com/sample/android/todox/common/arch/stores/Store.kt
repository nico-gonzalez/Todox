package com.sample.android.todox.common.arch.stores

import com.sample.android.todox.common.ui.UIEvent
import io.reactivex.Flowable

interface Store<T> {

  fun getState(): Flowable<T>

  fun dispatch(event: UIEvent): Flowable<out Any?>
}