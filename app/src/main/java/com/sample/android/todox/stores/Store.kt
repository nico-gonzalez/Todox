package com.sample.android.todox.stores

import com.sample.android.todox.common.UIEvent
import io.reactivex.Flowable

interface Store<T> {

  fun getState(): Flowable<T>

  fun dispatch(event: UIEvent): Flowable<out Any?>
}