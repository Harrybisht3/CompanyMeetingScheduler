package com.android.companymeetingscheduler.di.module

import android.content.Context
import androidx.room.Room
import com.android.companymeetingscheduler.data.db.AppDatabase
import com.android.companymeetingscheduler.data.db.dao.MeetingDao
import com.android.companymeetingscheduler.di.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides

@Module(includes = [ContextModule::class])

class DataBaseModule {
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserDao(appDataBase: AppDatabase): MeetingDao {
        return appDataBase.meetingDao()
    }

}