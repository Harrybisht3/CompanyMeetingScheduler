package com.android.companymeetingscheduler.ui.schedule

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.companymeetingscheduler.domain.model.Meeting
import com.android.companymeetingscheduler.domain.usecases.UseCases
import com.android.companymeetingscheduler.ui.BaseViewModel
import com.android.companymeetingscheduler.ui.Utility
import com.android.companymeetingscheduler.ui.base.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ScheduleMeetingViewModel @Inject constructor(private val scheduleUseCase: UseCases.ScheduleUseCase) :
    BaseViewModel() {
    private val _navigateToDetails = MutableLiveData<Event<String>>()
    val loadingIndicator: ObservableBoolean = ObservableBoolean(false)
    val isPastDate: ObservableBoolean = ObservableBoolean(false)
    val data: MutableLiveData<Meeting> = MutableLiveData()
    var meetingList: ArrayList<Meeting> = arrayListOf()
    val navigateToDetails: LiveData<Event<String>>
        get() = _navigateToDetails

    fun userClicksOnDate() {
        _navigateToDetails.value =
            Event(Event.EVENT_DATE_SELECT)  // Trigger the event by setting a new Event as a new value
    }

    fun userClicksOnStartTime() {
        _navigateToDetails.value =
            Event(Event.EVENT_SELECT_START_TIME)  // Trigger the event by setting a new Event as a new value
    }

    fun userClicksOnEndTime() {
        _navigateToDetails.value =
            Event(Event.EVENT_SELECT_END_TIME)  // Trigger the event by setting a new Event as a new value
    }

    fun userClicksBack() {
        _navigateToDetails.value =
            Event(Event.EVENT_BACK_PRESS)  // Trigger the event by setting a new Event as a new value
    }

    fun userClicksSubmit() {
        data.value?.date?.let { getAllMeetingForSelectedDate(it) }

    }

    private fun checkMeetingClash() {
        var conflict: Int = 0
        for (meeting in meetingList) {
            data.value?.date?.let { meeting.date = it }
            val startDate = Utility.convertStringToDate(meeting.date + " " + meeting.start_time);
            val endDate = Utility.convertStringToDate(meeting.date + " " + meeting.end_time);
            val meetingStartDate = Utility.convertStringToDate(data.value?.date + " " + data.value?.start_time)
            val meetingEndDate = Utility.convertStringToDate(data.value?.date + " " + data.value?.end_time)
            if (meetingEndDate.after(meetingStartDate)) {
                if (meetingStartDate.after(endDate) || meetingEndDate.before(startDate)) {
                    conflict = 0
                } else {
                    conflict = 1
                    break
                }
            } else {
                conflict = -1
                break
            }
        }
        when (conflict) {
            0 -> _navigateToDetails.value = Event(Event.EVENT_NO_MEETING_CONFLICT)
            1 -> _navigateToDetails.value = Event(Event.EVENT_MEETING_CONFLICT)
            -1 -> _navigateToDetails.value = Event(Event.EVENT_INVALID_DATE)
        }

    }

    private fun getAllMeetingForSelectedDate(date: String) {
        loadingIndicator.set(true)
        disposable.add(
            scheduleUseCase.getMeeting(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    DisposableSingleObserver<List<Meeting>>() {
                    override fun onSuccess(value: List<Meeting>) {
                        meetingList.clear()
                        meetingList.addAll(value)
                        loadingIndicator.set(false)
                        checkMeetingClash()
                    }

                    override fun onError(e: Throwable) {
                        loadingIndicator.set(false)

                    }
                })
        )

    }

    fun checkPastDate() {
        isPastDate.set(false)
        val meetingStartDate = Utility.convertStringToDate(data.value?.date + " " + data.value?.start_time)
        if (meetingStartDate.after(Utility.getCurrentDateTime())) {
            isPastDate.set(false)
        } else {
            isPastDate.set(true)
        }

    }
}