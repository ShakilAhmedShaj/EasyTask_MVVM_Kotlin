package com.shajt3ch.todomvvm.view.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.shajt3ch.todomvvm.R
import com.shajt3ch.todomvvm.databinding.HomeFragmentBinding
import com.shajt3ch.todomvvm.model.local.entity.TaskEntity
import com.shajt3ch.todomvvm.model.remote.response.todo.TaskResponse
import com.shajt3ch.todomvvm.view.adaptor.TaskAdapter
import com.shajt3ch.todomvvm.view.adaptor.TaskCallBack
import com.shajt3ch.todomvvm.viewmodel.home.HomeViewModel
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.home_fragment.*
import org.jetbrains.anko.support.v4.alert

class HomeFragment : Fragment(), TaskCallBack {

    companion object {
        const val TAG = "HomeFragment"
    }

    private lateinit var viewModel: HomeViewModel
    //private var taskList: ArrayList<TaskResponse> = ArrayList()
    private var taskList: ArrayList<TaskEntity> = ArrayList()

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

        observer()
        //getAllTask()
    }

    /*
    private fun getAllTask() {
        viewModel.getAllTask().observe(viewLifecycleOwner, Observer {

            // clear data for task list
            taskList.clear()

            taskList = it!!.toCollection(taskList)

            setRecyclerView()

        })
    }

     */

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
                    data?.bg_color.toString(),
                    data?.id.toString()

                )
            )

            Log.e(TAG, "Position: $position is a single click")
        }

    }

    private fun observer() {
        viewModel.isError.observe(viewLifecycleOwner, Observer {
            errorDialog(it)
        })

        viewModel.progress.observe(viewLifecycleOwner, Observer {
            pb_home.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.taskListFromDb.observe(viewLifecycleOwner, Observer {
            taskList.clear()

            taskList = it!!.toCollection(taskList)

            setRecyclerView()
        })
    }

    private fun errorDialog(errorMsg: String) {
        alert {
            title = getString(R.string.title_error_dialog)
            message = errorMsg
            isCancelable = false
            positiveButton(getString(R.string.btn_ok)) { dialog ->
                dialog.dismiss()
            }
        }.show()
    }
}
