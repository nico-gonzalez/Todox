package com.sample.android.todox.common

import android.support.v7.app.AppCompatActivity
import com.sample.android.todox.TodoxApplication
import com.sample.android.todox.common.di.Injector

open class BaseActivity : AppCompatActivity() {

  fun application(): TodoxApplication {
    return applicationContext as TodoxApplication
  }

  fun injector(): Injector {
    return application().injector()!!
  }
}