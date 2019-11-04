package com.android.companymeetingscheduler.ui

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Utility {
    companion object {
        fun getCurrentDate(): String {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                return current.format(formatter)

            } else {
                var mDate = Date();
                val formatter = SimpleDateFormat("dd/MM/yyyy")
                return formatter.format(mDate)


            }
        }

        fun convertStringToDate(string: String): Date {
            return SimpleDateFormat("dd/MM/yyyy HH:mm").parse(string)
        }
        fun getCurrentDateTime(): Date {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                return convertStringToDate(current.format(formatter))

            } else {
                var mDate = Date();
                val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm")
                return convertStringToDate(formatter.format(mDate))


            }
        }

    }
}