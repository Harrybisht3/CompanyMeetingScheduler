package com.android.companymeetingscheduler.domain.usecases

import com.android.companymeetingscheduler.domain.model.Meeting
import io.reactivex.Single

class UseCases {
    interface ScheduleUseCase {
        fun getMeeting(date: String): Single<List<Meeting>>
    }
}