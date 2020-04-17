package com.shajt3ch.todomvvm.view.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.shajt3ch.todomvvm.R
import com.shajt3ch.todomvvm.databinding.HomeFragmentBinding
import com.shajt3ch.todomvvm.model.remote.response.todo.TaskResponse
import com.shajt3ch.todomvvm.view.adaptor.TaskAdapter
import com.shajt3ch.todomvvm.view.adaptor.TaskCallBack
import com.shajt3ch.todomvvm.viewmodel.home.HomeViewModel

class HomeFragment : Fragment(), TaskCallBack {

    companion object {
        fun newInstance() = HomeFragment()
        const val TAG = "HomeFragment"
    }

    private lateinit var viewModel: HomeViewModel
    private var taskList: ArrayList<TaskResponse> = ArrayList()

    //data bind
    private lateinit var binding: HomeFragmentBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)

        //recyclerview

        recyclerView = binding.taskRecyclerView
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager


        return binding.root
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

                // clear data for task list
                taskList.clear()

                taskList = it.body()!!.toCollection(taskList)

                setRecyclerView()

                /*for (task in taskList) {
                    Log.e(TAG, "Title : ${task.title} Body : ${task.body}")
                }*/

            } else {

                Log.e(TAG, "error code : ${it.code()}  message : ${it.errorBody()}")
            }


        })
    }

    private fun setRecyclerView() {

        taskAdapter = TaskAdapter(taskList)
        recyclerView.adapter = taskAdapter

        taskAdapter.setTaskCallBack(this)

    }

    override fun onTaskClick(view: View, position: Int, isLongClick: Boolean) {

        if (isLongClick) {
            Log.e(TAG, "Position: $position is a long click")
        } else {

            val data = viewModel.taskList.value?.get(position)

            findNavController().navigate(
                HomeFragmentDirections.actionNavigationHomeToTaskDetailFragment(
                    data?.createdAt.toString(),
                    data?.title.toString(),
                    data?.body.toString(),
                    data?.status.toString(),
                    data?.userId.toString(),
                    data?.bg_color.toString()

                )
            )


            Log.e(TAG, "Position: $position is a single click")
        }

    }

}
