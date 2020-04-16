package com.shajt3ch.todomvvm.view.ui.task

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.shajt3ch.todomvvm.R
import com.shajt3ch.todomvvm.viewmodel.todo.TaskViewModel
import kotlinx.android.synthetic.main.task_fragment.view.*

class TaskFragment : Fragment() {

    companion object {
        fun newInstance() = TaskFragment()
    }

    private lateinit var viewModel: TaskViewModel
    private val taskList: ArrayList<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.task_fragment, container, false)

        resources.getStringArray(R.array.task_status_list).toCollection(taskList)
        view.spinner_task.setItems(taskList)


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
