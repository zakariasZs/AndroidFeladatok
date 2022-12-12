package com.example.tracker.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.core.graphics.red
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.api.model.MyGroupsResponse
import com.example.tracker.api.model.TasksResponse
import com.example.tracker.util.StringUtil.Companion as utils
import com.example.tracker.util.TaskUtil as utilsTask


class TasksAdapter(
    private var list: ArrayList<TasksResponse>,
    private val context: Context,
    private var listener: OnItemClickListener,
    private val listener2: OnItemLongClickListener
) :
    RecyclerView.Adapter<TasksAdapter.DataViewHolder>() {

    var currentItemId: Int = -1

    // 1. user defined ViewHolder type - Embedded class!
    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener, View.OnLongClickListener {
        val taskView_title: TextView = itemView.findViewById(R.id.taskName)
        val taskView_description: TextView = itemView.findViewById(R.id.taskDescription)
        val taskView_creationDate: TextView = itemView.findViewById(R.id.taskDate)
        val taskView_assignee: TextView = itemView.findViewById(R.id.taskAssignee)
        val taskView_priority: TextView = itemView.findViewById(R.id.taskPriority)
        val taskView_deadline: TextView = itemView.findViewById(R.id.taskDeadLine)

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        override fun onClick(p0: View?) {
            val currentPosition = this.adapterPosition
            listener.onItemClick(currentPosition)
        }

        override fun onLongClick(p0: View?): Boolean {
            val currentPosition = this.adapterPosition
            listener2.onItemLongClick(currentPosition)
            return true
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(position: Int)
    }

    // 2. Called only a few times = number of items on screen + a few more ones
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return DataViewHolder(itemView)
    }




    // 3. Called many times, when we scroll the list
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = list[position]

        holder.taskView_title.text = currentItem.title
        holder.taskView_description.text = currentItem.description
        holder.taskView_creationDate.text = utils.convertLongToTime(currentItem.createdTime)
        holder.taskView_assignee.text = currentItem.assignedToUserID.toString()
        val itemTypeNr = currentItem.priority
        holder.taskView_priority.text = utilsTask.ItemPriority.values().get(itemTypeNr).toString()
        holder.taskView_priority.currentTextColor
        holder.taskView_deadline.text = utils.convertLongToTime(currentItem.deadline)

        holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.tasks_item_anim)

//        Glide.with(this.context)
//            .load(R.drawable.ic_user)
//            .override(200, 200)
//            .into(holder.imageView);
    }

    override fun getItemCount() = list.size

    // Update the list
    fun setData(newList: ArrayList<TasksResponse>) {
        list = newList
    }


}