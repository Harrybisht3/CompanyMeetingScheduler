package com.android.companymeetingscheduler.data.repository

import com.android.companymeetingscheduler.domain.model.Meeting
import com.android.companymeetingscheduler.domain.repository.AppRepository
import io.reactivex.Single
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    val service: @JvmSuppressWildcards com.android.companymeetingscheduler.data.api.Service//, val meetingDao: MeetingDao
) :
    AppRepository {
    override fun getMeeting(date: String): Single<List<Meeting>> {
        return service.getMeeting(date)//.map { saveMeeting(it, date) }
    }

    private fun saveMeeting(meeting: List<Meeting>, date: String): List<Meeting> {

        if (meeting.size > 0) {
            for (meet in meeting) {
                meet.date = date
            }
         //   meetingDao.insertAllMeetings(meeting)
        }
        return meeting
    }
}