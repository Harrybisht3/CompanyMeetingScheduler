package com.android.companymeetingscheduler.di.module

import com.android.companymeetingscheduler.data.repository.AppRepositoryImpl
import com.android.companymeetingscheduler.data.usecases.ScheduleUseCaseImpl
import com.android.companymeetingscheduler.domain.repository.AppRepository
import com.android.companymeetingscheduler.domain.usecases.UseCases
import dagger.Binds
import dagger.Module

@Module
interface RepoModule {
    @Binds
    fun bindScheduleUseCase(scheduleUseCaseImpl: ScheduleUseCaseImpl): UseCases.ScheduleUseCase

    @Binds
    fun bindAppRepository(appRepositoryImpl: AppRepositoryImpl): AppRepository
}