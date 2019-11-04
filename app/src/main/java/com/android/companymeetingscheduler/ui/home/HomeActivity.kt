package com.android.companymeetingscheduler.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.android.companymeetingscheduler.R
import com.android.companymeetingscheduler.databinding.ActivityHomeBinding
import com.android.companymeetingscheduler.ui.home.adapter.MeetingListAdapter
import com.android.companymeetingscheduler.ui.schedule.ScheduleMeetingActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by lazy { ViewModelProviders.of(this,viewModelFactory).get(HomeViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.viewModel = viewModel
        lifecycle.addObserver(viewModel)
        binding.lifecycleOwner = this
        viewModel.navigateToDetails.observe(this, Observer { it.getContentIfNotHandled()?.let { startMeetingScheduleAcivity() } })
        setAdapter()
    }
    private fun setAdapter(){
        val adapter = MeetingListAdapter()
        list_schedule.adapter = adapter
    }
    private fun startMeetingScheduleAcivity(){
        val intent= Intent(this, ScheduleMeetingActivity::class.java)
        startActivity(intent)
    }
}
