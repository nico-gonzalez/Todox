package com.sample.android.todox.common.schedulers

import io.reactivex.Scheduler

interface SchedulerProvider {

  fun io(): Scheduler

  fun ui(): Scheduler

  fun computation(): Scheduler
}