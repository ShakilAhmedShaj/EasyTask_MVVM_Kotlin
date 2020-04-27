package com.shajt3ch.todomvvm.model.local.entity

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * Created by Shakil Ahmed Shaj on 26,April,2020.
 * shakilahmedshaj@gmail.com
 */

@Entity(tableName = "task_entity")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "taskId")
    val taskId: Int,

    @ColumnInfo(name = "title")
    val title: String, // Task 1

    @ColumnInfo(name = "body")
    val body: String, // Body 1

    @ColumnInfo(name = "status")
    val status: String, // PENDING

    @ColumnInfo(name = "userId")
    val userId: Int, // 1

    @ColumnInfo(name = "createdAt")
    val createdAt: String, // 2020-04-16 15:25:13

    @ColumnInfo(name = "updatedAt")
    val updatedAt: String, // 2020-04-16 15:25:13

    @ColumnInfo(name = "bg_color")
    var bg_color: Int = 0

) {

    /*
    later
     */
    companion object {

        @JvmStatic
        @BindingAdapter("viewBackground")
        fun TextView.setBgColor(color: Int?) {
            if (color != null) {
                this.setBackgroundResource(color)
            }
        }
    }

}