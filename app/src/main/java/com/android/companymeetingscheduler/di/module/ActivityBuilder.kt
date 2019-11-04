package com.android.companymeetingscheduler.di.module


import com.android.companymeetingscheduler.ui.home.HomeActivity
import com.android.companymeetingscheduler.ui.schedule.ScheduleMeetingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector()
    abstract fun bindHomeActivity(): HomeActivity
    @ContributesAndroidInjector()
    abstract fun bindScheduleMeetingActivity(): ScheduleMeetingActivity
}


