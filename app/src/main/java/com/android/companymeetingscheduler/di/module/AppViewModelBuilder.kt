package com.android.companymeetingscheduler.di.module

import androidx.lifecycle.ViewModel
import com.android.companymeetingscheduler.di.qualifier.ViewModelKey
import com.android.companymeetingscheduler.ui.home.HomeViewModel
import com.android.companymeetingscheduler.ui.schedule.ScheduleMeetingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AppViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ScheduleMeetingViewModel::class)
    abstract fun bindScheduleMeetingViewModel(scheduleMeetingViewModel: ScheduleMeetingViewModel): ViewModel
}