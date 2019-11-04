package com.android.companymeetingscheduler.ui.home

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableList
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
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class HomeViewModel @Inject constructor(private val scheduleUseCase: UseCases.ScheduleUseCase) : BaseViewModel() {
    val loadingIndicator: ObservableBoolean = ObservableBoolean(true)
    val data: ObservableList<Meeting> = ObservableArrayList()
    val meetingList: MutableLiveData<List<Meeting>> = MutableLiveData()
    var date: MutableLiveData<String> = MutableLiveData()
    private val _navigateToDetails = MutableLiveData<Event<String>>()

    val navigateToDetails: LiveData<Event<String>>
        get() = _navigateToDetails

    fun userClicksScheduleMeeting() {
        _navigateToDetails.value =
            Event(Event.EVENT_NAVIGATE_SCHEDULE_MEETING)  // Trigger the event by setting a new Event as a new value
    }

    init {
        getCurrentDate()
        getMeetings(date.value!!)
    }

    private fun getMeetings(date: String) {
        loadingIndicator.set(true)
        disposable.add(
            scheduleUseCase.getMeeting(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    DisposableSingleObserver<List<Meeting>>() {
                    override fun onSuccess(value: List<Meeting>) {
                        data.clear()
                        data.addAll(value)
                        meetingList.setValue(value)
                        loadingIndicator.set(false)

                    }

                    override fun onError(e: Throwable) {
                        loadingIndicator.set(false)

                    }
                })
        )
    }

    fun onPreviousDate() {
        val decDate = decrementDateByOne(convertStringToDate())
        date.value = formatDate(decDate)
        date?.postValue(formatDate(decDate))
        Log.e("Date", date.value)
        getMeetings(date.value!!)

    }

    fun onNextDate() {
        val incDate = incrementDateByOne(convertStringToDate())
        date.value = formatDate(incDate)
        date?.postValue(formatDate(incDate))
        Log.e("Date", date.value)
        getMeetings(date.value!!)
    }


    fun getCurrentDate() {
        date.value = Utility.getCurrentDate()
        date.postValue(Utility.getCurrentDate())
    }

    private fun formatDate(dateValue: Date): String {
        val mdformat = SimpleDateFormat("dd/MM/yyyy")
        return mdformat.format(dateValue)
    }

    private fun convertStringToDate(): Date {
        return SimpleDateFormat("dd/MM/yyyy").parse(date?.value)
    }

    /**
     * Get next date from current selected date
     *
     * @param date date
     */
    fun incrementDateByOne(date: Date): Date {
        val c = Calendar.getInstance()
        c.time = date
        c.add(Calendar.DATE, 1)
        return c.time
    }

    /**
     * Get previous date from current selected date
     *
     * @param date date
     */
    fun decrementDateByOne(date: Date): Date {
        val c = Calendar.getInstance()
        c.time = date
        c.add(Calendar.DATE, -1)
        return c.time
    }
}