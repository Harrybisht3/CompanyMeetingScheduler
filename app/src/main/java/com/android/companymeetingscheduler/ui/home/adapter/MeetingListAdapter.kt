package com.android.companymeetingscheduler.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.companymeetingscheduler.R
import com.android.companymeetingscheduler.databinding.MeetingItemRowBinding
import com.android.companymeetingscheduler.domain.model.Meeting
import com.android.companymeetingscheduler.ui.base.BindableAdapter

internal class MeetingListAdapter : RecyclerView.Adapter<MeetingListAdapter.MeetingViewHolder>(),
    BindableAdapter<ArrayList<Meeting>> {
    override fun setData(data: ArrayList<Meeting>) {
        items = data;
        notifyDataSetChanged()
    }

    private var items: ArrayList<Meeting> = arrayListOf<Meeting>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MeetingViewHolder {
        val meetingItemRowBinding = DataBindingUtil.inflate<MeetingItemRowBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.meeting_item_row, viewGroup, false
        )
        return MeetingViewHolder(meetingItemRowBinding)
    }

    override fun onBindViewHolder(employeeViewHolder: MeetingViewHolder, i: Int) {
        val currentItem = items[i]
        employeeViewHolder.meetingItemRowBinding.meeting = currentItem
    }

    override fun getItemCount(): Int = items.size


      class MeetingViewHolder(internal val meetingItemRowBinding: MeetingItemRowBinding) :
        RecyclerView.ViewHolder(meetingItemRowBinding.root)

}