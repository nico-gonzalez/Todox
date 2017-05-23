package com.sample.android.todox.stores

import io.reactivex.Observable

interface Store<T> {

    fun getState(): Observable<T>
}