package com.android.companymeetingscheduler.domain.repository

import com.android.companymeetingscheduler.domain.model.Meeting
import io.reactivex.Single

interface AppRepository {
    fun getMeeting(date: String):Single<List<Meeting>>
}