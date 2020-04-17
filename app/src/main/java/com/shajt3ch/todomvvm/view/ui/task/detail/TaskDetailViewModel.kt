package com.shajt3ch.todomvvm.view.ui.task.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskDetailViewModel : ViewModel() {
    companion object {
        const val TAG = "TaskDetailViewModel"
    }

    val dataTime: MutableLiveData<String> = MutableLiveData()
    val title: MutableLiveData<String> = MutableLiveData()
    val body: MutableLiveData<String> = MutableLiveData()
    val status: MutableLiveData<String> = MutableLiveData()
    val userId: MutableLiveData<String> = MutableLiveData()
    val bgColor: MutableLiveData<String> = MutableLiveData()

}
