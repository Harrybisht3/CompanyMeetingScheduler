package com.android.companymeetingscheduler.data.api

import com.android.companymeetingscheduler.domain.model.Meeting
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
   @GET("/api/schedule")
    fun getMeeting(@Query("date") date: String): Single<List<Meeting>>

}