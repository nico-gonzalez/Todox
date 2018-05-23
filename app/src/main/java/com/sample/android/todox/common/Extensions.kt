package com.sample.android.todox.common

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun AppCompatActivity.application(): TodoxApplication = applicationContext as TodoxApplication

fun Fragment.application(): TodoxApplication = context?.applicationContext as TodoxApplication

fun Disposable.addTo(compositeDisposable: CompositeDisposable) = apply {
  compositeDisposable.add(this)
}
