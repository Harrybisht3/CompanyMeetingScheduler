package com.android.companymeetingscheduler.ui.base

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

class BindingAdapterClass {
    companion object {
        @BindingAdapter("data")
        @JvmStatic
        fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, data: T) {
            if (recyclerView.adapter!! is BindableAdapter<*>) {
                (recyclerView.adapter as BindableAdapter<T>).setData(data)
            }
        }
    }
}