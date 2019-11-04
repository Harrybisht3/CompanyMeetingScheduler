package com.android.companymeetingscheduler.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.phone.app.mms.database.converters.Converters

@Entity
@TypeConverters(Converters::class)
data class Meeting(
    @PrimaryKey(autoGenerate = true) var id: Long, @ColumnInfo(name = "date") var date: String,
    var start_time: String,
    var end_time: String,
    val participants: List<String>
) {
}