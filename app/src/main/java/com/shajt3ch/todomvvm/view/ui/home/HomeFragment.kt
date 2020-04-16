package com.shajt3ch.todomvvm.view.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.shajt3ch.todomvvm.R
import com.shajt3ch.todomvvm.model.remote.response.todo.TaskResponse
import com.shajt3ch.todomvvm.viewmodel.home.HomeViewModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
        const val TAG = "HomeFragment"
    }

    private lateinit var viewModel: HomeViewModel
    private var taskList: ArrayList<TaskResponse> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.init(context!!)

        getAllTask()
    }

    private fun getAllTask() {
        viewModel.getAllTask().observe(viewLifecycleOwner, Observer {

            if (it.code() == 200) {
                taskList = it.body()!!.toCollection(taskList)

                for (task in taskList) {
                    Log.e(TAG, "Title : ${task.title} Body : ${task.body}")
                }

            } else {

                Log.e(TAG, "error code : ${it.code()}  message : ${it.errorBody()}")
            }


        })
    }

}
