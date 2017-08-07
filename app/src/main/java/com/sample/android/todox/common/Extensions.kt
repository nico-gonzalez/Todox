package com.sample.android.todox.common

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.sample.android.todox.TodoxApplication

fun AppCompatActivity.application(): TodoxApplication = this.applicationContext as TodoxApplication

fun Fragment.application(): TodoxApplication = this.context.applicationContext as TodoxApplication
