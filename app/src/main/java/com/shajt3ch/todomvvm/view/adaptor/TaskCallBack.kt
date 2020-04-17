package com.shajt3ch.todomvvm.view.adaptor

import android.view.View

/**
 * Created by Shakil Ahmed Shaj on 17,April,2020.
 * shakilahmedshaj@gmail.com
 */
interface TaskCallBack {

    fun onTaskClick(view: View, position: Int, isLongClick: Boolean)
}