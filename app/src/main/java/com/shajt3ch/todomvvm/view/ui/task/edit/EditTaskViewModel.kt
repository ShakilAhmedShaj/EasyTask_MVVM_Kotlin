package com.shajt3ch.todomvvm.view.ui.task.edit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditTaskViewModel : ViewModel() {

    companion object {
        const val TAG = "EditTaskViewModel"
    }


    val id: MutableLiveData<String> = MutableLiveData()
    val title: MutableLiveData<String> = MutableLiveData()
    val body: MutableLiveData<String> = MutableLiveData()
    val status: MutableLiveData<String> = MutableLiveData()
    val index: MutableLiveData<Int> = MutableLiveData()
    val taskList: ArrayList<String> = ArrayList()


    fun getIndexFromTaskList() {

        index.value = taskList.indexOf(status.value)
    }

}
