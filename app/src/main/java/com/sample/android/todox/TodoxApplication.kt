package com.sample.android.todox

import android.app.Application
import com.sample.android.todox.common.di.ApplicationComponent
import com.sample.android.todox.common.di.ApplicationModule
import com.sample.android.todox.common.di.DaggerApplicationComponent


class TodoxApplication : Application() {

  val applicationComponent: ApplicationComponent by lazy {
    DaggerApplicationComponent.builder()
        .applicationModule(ApplicationModule(this))
        .build()
  }
}
