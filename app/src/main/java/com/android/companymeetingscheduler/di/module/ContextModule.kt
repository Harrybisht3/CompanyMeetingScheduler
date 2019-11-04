package com.android.companymeetingscheduler.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class ContextModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

}