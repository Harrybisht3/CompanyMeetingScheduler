package com.android.companymeetingscheduler.di.component

import com.android.companymeetingscheduler.CompanyMeetingSchedulerApp
import com.android.companymeetingscheduler.di.module.ActivityBuilder
import com.android.companymeetingscheduler.di.module.ApiModule
import com.android.companymeetingscheduler.di.module.ContextModule
import com.android.companymeetingscheduler.di.module.RepoModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ApiModule::class, RepoModule::class,  ActivityBuilder::class,ContextModule::class])
interface AppComponent : AndroidInjector<CompanyMeetingSchedulerApp>


