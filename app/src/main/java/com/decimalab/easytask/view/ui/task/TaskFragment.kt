package com.decimalab.easytask.view.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.decimalab.easytask.R
import com.decimalab.easytask.model.remote.request.task.AddTaskRequest
import com.decimalab.easytask.viewmodel.task.TaskViewModel
import kotlinx.android.synthetic.main.task_fragment.*
import kotlinx.android.synthetic.main.task_fragment.view.*
import kotlinx.android.synthetic.main.task_fragment.view.txt_body
import kotlinx.android.synthetic.main.task_fragment.view.txt_title
import org.jetbrains.anko.support.v4.alert

class TaskFragment : Fragment() {

    companion object {
        const val TAG = "TaskFragment"
    }

    private lateinit var viewModel: TaskViewModel
    private val taskList: ArrayList<String> = ArrayList()
    private var userId: Int = 0
    private lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.task_fragment, container, false)

        resources.getStringArray(R.array.task_status_list).toCollection(taskList)

        v.fab_addTask.setOnClickListener {
            prepareAddTask(v)
        }

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        viewModel.userId.observe(viewLifecycleOwner, Observer {
            userId = it
        })

        observeProgressBar(v)

    }

    private fun prepareAddTask(view: View) {

        val title = view.txt_title.text.toString()
        val body = view.txt_body.text.toString()
        val status = view.spinner_task.selectedItem.toString()

        val addTaskRequest = AddTaskRequest(userId, title, body, status)

        addTask(addTaskRequest)


    }

    private fun addTask(addTaskRequest: AddTaskRequest) {

        viewModel.addTask(addTaskRequest).observe(viewLifecycleOwner, Observer {

            if (it!!) {
                successDialog()
            }
            else
            {
                unSuccessFulDialog()
            }
        })
    }

    private fun observeProgressBar(view: View) {

        viewModel.progress.observe(viewLifecycleOwner, Observer {

            if (it) {

                view.progressBar.visibility = View.VISIBLE

            } else {
                view.progressBar.visibility = View.GONE

            }

        })

        viewModel.isError.observe(viewLifecycleOwner, Observer {
            errorDialog(it)
        })

    }

    private fun successDialog() {
        alert {
            title = getString(R.string.title_success_dialog)
            message = getString(R.string.msg_add_post_success)
            isCancelable = false
            positiveButton(getString(R.string.btn_ok)) { dialog ->
                //clear the edit text
                txt_title.text?.clear()
                txt_body.text?.clear()

                dialog.dismiss()
            }
        }.show()
    }

    private fun unSuccessFulDialog() {
        alert {
            title = getString(R.string.title_un_successful_dialog)
            message = getString(R.string.msg_add_post_un_successful)
            isCancelable = false
            positiveButton(getString(R.string.btn_ok)) { dialog ->
                dialog.dismiss()
            }
        }.show()
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
