package com.android.companymeetingscheduler

import com.android.companymeetingscheduler.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class CompanyMeetingSchedulerApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.create()
    }

    override fun onCreate() {
        super.onCreate()

    }
}