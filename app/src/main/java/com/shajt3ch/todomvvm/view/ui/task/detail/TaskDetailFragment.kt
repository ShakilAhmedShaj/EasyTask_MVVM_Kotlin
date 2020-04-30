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
import org.jetbrains.anko.support.v4.alert

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

        val args = TaskDetailFragmentArgs.fromBundle(requireArguments())
        viewModel.dataTime.value = args.dateTime
        viewModel.title.value = args.title
        viewModel.body.value = args.body
        viewModel.status.value = args.status
        viewModel.userIdField.value = args.userId
        viewModel.bgColor.value = args.statusColor
        viewModel.apiTaskId.value = args.id
        viewModel.taskId.value = args.taskId

        viewModel.checkUserId()

        observerData()

        fb_edit.setOnClickListener {
            findNavController().navigate(
                TaskDetailFragmentDirections.actionTaskDetailFragmentToEditTaskFragment(
                    viewModel.apiTaskId.value.toString(),
                    viewModel.title.value.toString(),
                    viewModel.body.value.toString(),
                    viewModel.status.value.toString(),
                    viewModel.taskId.value.toString()
                )
            )
        }

        fb_delete.setOnClickListener {
            deleteConfirmDialog()
        }

    }

    private fun observerData() {
        viewModel.apiTaskId.observe(viewLifecycleOwner, Observer {

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

        viewModel.isValidUser.observe(viewLifecycleOwner, Observer {

            fb_edit.visibility = if (it) View.VISIBLE else View.GONE
            fb_delete.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.isDeleted.observe(viewLifecycleOwner, Observer {
            if (it) {
                deleteSuccessDialog()
            } else {
                deleteErrorDialog()
            }
        })
    }

    private fun deleteConfirmDialog() {
        alert {
            isCancelable = false
            title = getString(R.string.alert_delete_title)
            message = getString(R.string.alert_delete_msg)
            positiveButton("YES") {
                it.dismiss()
                viewModel.deleteTask()
            }
            negativeButton("NO") {
                it.dismiss()
            }
        }.show()
    }

    private fun deleteSuccessDialog() {
        alert {
            isCancelable = false
            title = getString(R.string.alert_success_title)
            message = getString(R.string.alert_delete_success)
            positiveButton("OK") {
                it.dismiss()
                findNavController().navigate(TaskDetailFragmentDirections.actionTaskDetailFragmentToNavigationHome())
            }
        }.show()
    }

    private fun deleteErrorDialog() {
        alert {
            isCancelable = false
            title = getString(R.string.alert_error_title)
            message = getString(R.string.alert_delete_error)
            positiveButton("OK") {
                it.dismiss()
            }
        }.show()
    }

}
