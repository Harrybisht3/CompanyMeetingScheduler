package com.android.companymeetingscheduler.data.db.dao

import androidx.room.*
import com.android.companymeetingscheduler.domain.model.Meeting

@Dao
interface MeetingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMeetings(listMap: List<Meeting>)

    @Delete
    fun deleteMeeting(food: Meeting): Int

    @Query("SELECT * from Meeting")
    fun selectAllMeetings(): MutableList<Meeting>

  /*  @Query("SELECT * from Meeting WHERE date=date")
    fun selectMeetings(date: String): MutableList<Meeting>*/


}