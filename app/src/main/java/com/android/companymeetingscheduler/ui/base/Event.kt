package com.android.companymeetingscheduler.ui.base

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content

    companion object {
        const val EVENT_NAVIGATE_SCHEDULE_MEETING = "scheduleMetting"
        const val EVENT_DATE_SELECT = "selectDate"
        const val EVENT_SELECT_START_TIME = "startTime"
        const val EVENT_SELECT_END_TIME = "endTime"
        const val EVENT_BACK_PRESS = "backPress"
        const val EVENT_MEETING_CONFLICT= "meetingConflict"
        const val EVENT_NO_MEETING_CONFLICT= "meetingNoConflict"
        const val EVENT_INVALID_DATE= "invalidDate"
    }
}
