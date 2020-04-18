package com.shajt3ch.todomvvm.view.ui.task.edit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.shajt3ch.todomvvm.R
import com.shajt3ch.todomvvm.viewmodel.task.EditTaskViewModel
import kotlinx.android.synthetic.main.edit_task_fragment.*

class EditTaskFragment : Fragment() {

    companion object {
        fun newInstance() = EditTaskFragment()
        const val TAG = "EditTaskFragment"
    }

    private lateinit var viewModel: EditTaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_task_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditTaskViewModel::class.java)
        viewModel.init(context!!)

        resources.getStringArray(R.array.task_status_list).toCollection(viewModel.taskList)


        val args = EditTaskFragmentArgs.fromBundle(arguments!!)
        viewModel.id.value = args.taskId
        viewModel.title.value = args.taskTitle
        viewModel.body.value = args.taskBody
        viewModel.status.value = args.taskStatus

        observeData()

        fab_editTask.setOnClickListener {
            viewModel.status.value = edit_spinner_task.selectedItem.toString()
            //viewModel.title.value = edit_title.text.toString()
            //viewModel.body.value = edit_body.text.toString()

            viewModel.editTask().observe(viewLifecycleOwner, Observer {

                if (it.code() == 201) {
                    Log.e(TAG, "${it.body()}")
                } else {
                    Log.e(TAG, "${it.errorBody()}")
                }

            })
        }

        observeData()
    }

    private fun observeData() {
        viewModel.id.observe(viewLifecycleOwner, Observer {

        })

        viewModel.title.observe(viewLifecycleOwner, Observer {
            edit_title.setText(it)

        })

        viewModel.body.observe(viewLifecycleOwner, Observer {
            edit_body.setText(it)

        })

        viewModel.status.observe(viewLifecycleOwner, Observer {

            viewModel.getIndexFromTaskList()

            edit_spinner_task.setSelection(viewModel.index.value!!)

        })


    }

}
