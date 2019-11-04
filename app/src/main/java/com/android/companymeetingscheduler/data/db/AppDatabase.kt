package com.android.companymeetingscheduler.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.companymeetingscheduler.data.db.dao.MeetingDao
import com.android.companymeetingscheduler.domain.model.Meeting

@Database(entities = [Meeting::class], version = AppDatabase.VERSION,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "meetings.db"
        const val VERSION = 1
    }
    abstract fun meetingDao(): MeetingDao
}