package com.android.companymeetingscheduler.data.usecases

import com.android.companymeetingscheduler.domain.model.Meeting
import com.android.companymeetingscheduler.domain.repository.AppRepository
import com.android.companymeetingscheduler.domain.usecases.UseCases
import io.reactivex.Single
import javax.inject.Inject

class ScheduleUseCaseImpl @Inject constructor(val appRepository: @JvmSuppressWildcards AppRepository) : UseCases.ScheduleUseCase {
    override fun getMeeting(date: String): Single<List<Meeting>> {
        return appRepository.getMeeting(date)
    }
}