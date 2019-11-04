package com.android.companymeetingscheduler.ui.schedule

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.android.companymeetingscheduler.R
import com.android.companymeetingscheduler.databinding.ActivityScheduleMeetingBinding
import com.android.companymeetingscheduler.domain.model.Meeting
import com.android.companymeetingscheduler.ui.base.Event
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_schedule_meeting.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ScheduleMeetingActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: ActivityScheduleMeetingBinding
    lateinit var meeting: Meeting

    private val viewModel: ScheduleMeetingViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(ScheduleMeetingViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_schedule_meeting)
        binding.viewModel = viewModel
        lifecycle.addObserver(viewModel)
        binding.lifecycleOwner = this
        meeting = Meeting(0, "MeetingDate", "Start Time", "EndTime", listOf())
        viewModel.navigateToDetails.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                when (it) {
                    Event.EVENT_DATE_SELECT -> showDatePicker()
                    Event.EVENT_SELECT_START_TIME -> getTime(Event.EVENT_SELECT_START_TIME)
                    Event.EVENT_SELECT_END_TIME -> getTime(Event.EVENT_SELECT_END_TIME)
                    Event.EVENT_BACK_PRESS -> finish()
                    Event.EVENT_MEETING_CONFLICT -> showSnackBar(resources.getString(R.string.conflict))
                    Event.EVENT_NO_MEETING_CONFLICT -> showSnackBar(resources.getString(R.string.no_conflict))
                    Event.EVENT_INVALID_DATE -> showSnackBar(resources.getString(R.string.invalid))

                }
            }
        })
        viewModel.data.postValue(meeting)
    }

    fun showDatePicker() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

            // Display Selected date in textbox
            meeting.date = "" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year
            viewModel.data.postValue(meeting)

        }, year, month, day)

        dpd.show()
    }

    fun getTime(string: String) {
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            when (string) {
                Event.EVENT_SELECT_START_TIME -> {
                    meeting.start_time =
                        SimpleDateFormat("HH:mm").format(cal.time).toString()
                    viewModel.data.postValue(meeting)
                }
                Event.EVENT_SELECT_END_TIME -> {
                    meeting.end_time = SimpleDateFormat("HH:mm").format(cal.time).toString()
                    viewModel.data.postValue(meeting)
                    viewModel.checkPastDate()
                }
            }

        }

        TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
    }

    fun showSnackBar(string: String) {
        Snackbar.make(
            button_schedule, // Parent view
            string, // Message to show
            Snackbar.LENGTH_SHORT // How long to display the message.
        ).show()
    }
}