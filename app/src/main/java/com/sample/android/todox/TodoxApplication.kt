package com.sample.android.todox

import android.app.Application
import com.sample.android.todox.common.di.DependencyInjector
import com.sample.android.todox.common.di.Injector

class TodoxApplication : Application() {

  private var injector: Injector? = null

  fun injector(): Injector? {
    if (injector == null) {
      injector = DependencyInjector(this)
    }

    return injector
  }
}