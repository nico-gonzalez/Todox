package com.sample.android.todox.reducers

import io.reactivex.Flowable

interface Reducer<in A, R> {

    fun reduce(action: A) : Flowable<R>
}