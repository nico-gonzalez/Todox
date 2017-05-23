package com.sample.android.todox.reducers

import io.reactivex.Observable

interface Reducer<A, R> {

    fun reduce(action: A) : Observable<R>
}