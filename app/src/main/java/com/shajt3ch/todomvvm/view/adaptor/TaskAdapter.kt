package com.shajt3ch.todomvvm.view.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.shajt3ch.todomvvm.R
import com.shajt3ch.todomvvm.databinding.CustomTaskListViewBinding
import com.shajt3ch.todomvvm.model.remote.response.todo.TaskResponse

/**
 * Created by Shakil Ahmed Shaj on 16,April,2020.
 * shakilahmedshaj@gmail.com
 */
class TaskAdapter(private val taskList: ArrayList<TaskResponse>) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {


    private lateinit var taskCallBack: TaskCallBack

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: CustomTaskListViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.custom_task_list_view, parent, false
        )

        return ViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: TaskResponse = taskList[position]

        when (data.status) {
            "PENDING" -> {
                data.bg_color = R.color.status_pending
            }
            "STARTED" -> {
                data.bg_color = R.color.status_started
            }
            else -> {
                data.bg_color = R.color.status_completed
            }
        }

        holder.taskListBinding.task = data

        holder.setTaskCallBack(taskCallBack)
    }

    fun setTaskCallBack(taskCallBack: TaskCallBack) {
        this.taskCallBack = taskCallBack
    }

    class ViewHolder(binding: CustomTaskListViewBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener, View.OnLongClickListener {
        var taskListBinding: CustomTaskListViewBinding = binding

        private lateinit var taskCallBack: TaskCallBack

        init {
            taskListBinding.root.setOnClickListener(this)
            taskListBinding.root.setOnLongClickListener(this)
        }

        fun setTaskCallBack(taskCallBack: TaskCallBack) {
            this.taskCallBack = taskCallBack
        }

        override fun onClick(v: View?) {

            if (v != null) {
                taskCallBack.onTaskClick(v, adapterPosition, false)
            }

        }

        override fun onLongClick(v: View?): Boolean {

            if (v != null) {
                taskCallBack.onTaskClick(v, adapterPosition, true)
            }
            return false
        }

    }


}