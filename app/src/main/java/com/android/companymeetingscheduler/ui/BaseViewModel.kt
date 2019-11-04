package com.android.companymeetingscheduler.ui

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel
    : ViewModel(), LifecycleObserver {

    val disposable: CompositeDisposable = CompositeDisposable()


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}