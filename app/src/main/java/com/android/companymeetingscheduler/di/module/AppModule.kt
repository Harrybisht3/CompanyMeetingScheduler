package com.android.companymeetingscheduler.di.module

import androidx.lifecycle.ViewModelProvider
import com.android.companymeetingscheduler.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(includes = [AppViewModelBuilder::class])
abstract class AppModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}