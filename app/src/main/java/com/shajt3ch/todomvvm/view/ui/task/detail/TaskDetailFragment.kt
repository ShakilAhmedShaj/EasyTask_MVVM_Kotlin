package com.shajt3ch.todomvvm.view.ui.task.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.shajt3ch.todomvvm.R
import com.shajt3ch.todomvvm.viewmodel.task.TaskDetailViewModel
import kotlinx.android.synthetic.main.task_detail_fragment.*

class TaskDetailFragment : Fragment() {

    companion object {
        const val TAG = "TaskDetailFragment"
    }

    private lateinit var viewModel: TaskDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.task_detail_fragment, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(TaskDetailViewModel::class.java)

        val args = TaskDetailFragmentArgs.fromBundle(arguments!!)
        viewModel.dataTime.value = args.dateTime
        viewModel.title.value = args.title
        viewModel.body.value = args.body
        viewModel.status.value = args.status
        viewModel.userIdField.value = args.userId
        viewModel.bgColor.value = args.statusColor
        viewModel.id.value = args.id

        viewModel.checkUserId()

        observerData()

        fb_edit.setOnClickListener {
            findNavController().navigate(
                TaskDetailFragmentDirections.actionTaskDetailFragmentToEditTaskFragment(
                    viewModel.id.value.toString(),
                    viewModel.title.value.toString(),
                    viewModel.body.value.toString(),
                    viewModel.status.value.toString()
                )
            )
        }
    }

    private fun observerData() {
        viewModel.id.observe(viewLifecycleOwner, Observer {

        })

        viewModel.dataTime.observe(viewLifecycleOwner, Observer {
            tv_date_time.text = it
        })
        viewModel.title.observe(viewLifecycleOwner, Observer {
            tv_title.text = it
        })

        viewModel.body.observe(viewLifecycleOwner, Observer {
            tv_body.text = it
        })

        viewModel.status.observe(viewLifecycleOwner, Observer {
            tv_status.text = it
        })

        viewModel.userIdField.observe(viewLifecycleOwner, Observer {
            tv_user_id.text = it
        })

        viewModel.bgColor.observe(viewLifecycleOwner, Observer {
            status_color.setBackgroundResource(it.toInt())

        })

        viewModel.isEditable.observe(viewLifecycleOwner, Observer {

            fb_edit.visibility = if (it) View.VISIBLE else View.GONE
        })


    }

}
