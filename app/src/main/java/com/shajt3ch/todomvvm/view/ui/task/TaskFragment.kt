package com.shajt3ch.todomvvm.view.ui.task

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.shajt3ch.todomvvm.R
import com.shajt3ch.todomvvm.model.remote.request.todo.AddTaskRequest
import com.shajt3ch.todomvvm.viewmodel.todo.TaskViewModel
import kotlinx.android.synthetic.main.task_fragment.view.*

class TaskFragment : Fragment() {

    companion object {
        const val TAG = "TAG"
        fun newInstance() = TaskFragment()
    }

    private lateinit var viewModel: TaskViewModel
    private val taskList: ArrayList<String> = ArrayList()
    private var userId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.task_fragment, container, false)

        resources.getStringArray(R.array.task_status_list).toCollection(taskList)
        view.spinner_task.setItems(taskList)

        view.fab_addTask.setOnClickListener {
            prepareAddTask(view)
        }


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        viewModel.init(activity?.applicationContext!!)

        viewModel.user_id.observe(viewLifecycleOwner, Observer {
            userId = it
        })

    }

    private fun prepareAddTask(view: View) {

        val title = view.txt_title.text.toString()
        val body = view.txt_body.text.toString()
        val selectedIndex = view.spinner_task.selectedIndex
        val status = taskList[selectedIndex]

        val addTaskRequest = AddTaskRequest(userId, title, body, status)

        addTask(addTaskRequest)


    }

    private fun addTask(addTaskRequest: AddTaskRequest) {
        viewModel.addTask(addTaskRequest).observe(viewLifecycleOwner, Observer {
            if (it.code() == 201) {
                val data = it.body()

                Log.d(TAG, "${data?.title} ${data?.body}")
            } else {
                Log.e(TAG, "error code : ${it.code()}  message : ${it.errorBody()}")
            }
        })
    }

}
