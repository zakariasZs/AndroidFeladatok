package com.example.tracker.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.api.model.ActivitiesResponse
import com.example.tracker.viewmodel.GetUsersViewModel
import com.example.tracker.util.StringUtil.Companion as utils


class ActivitiesAdapter(
    private var list: ArrayList<ActivitiesResponse>,
    private val context: Context,
    private val usersViewModel: GetUsersViewModel
) :
    RecyclerView.Adapter<ActivitiesAdapter.DataViewHolder>() {


    // 1. user defined ViewHolder type - Embedded class!
    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val activityView_type: TextView = itemView.findViewById(R.id.activityType)
        val activityView_postedBy: TextView = itemView.findViewById(R.id.activityPostedBy)
        val activityView_postedDate: TextView = itemView.findViewById(R.id.activityPostDate)
        val activityView_content: TextView = itemView.findViewById(R.id.activityContent)
        val activityView_contentType: TextView = itemView.findViewById(R.id.activityContentType)

    }

    // 2. Called only a few times = number of items on screen + a few more ones
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return when (viewType) {
            ActivityListItemType.DEPARTMENT.value -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.department_feed_item, parent, false)
                DataViewHolder(itemView)
            }
            ActivityListItemType.TASK.value -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.task_feed_item, parent, false)
                DataViewHolder(itemView)
            }
            ActivityListItemType.ANNOUNCMENT.value -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.announcement_feed_item, parent, false)
                DataViewHolder(itemView)
            }
            else -> {
                throw IllegalStateException("Type is not supported!")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentItem = list[position]

        return if (currentItem.type == 1) {
            ActivityListItemType.DEPARTMENT.value
        } else if (currentItem.type == 2){
            ActivityListItemType.TASK.value
        }else {
            ActivityListItemType.ANNOUNCMENT.value
        }
    }




    // 3. Called many times, when we scroll the list
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = list[position]
        usersViewModel.getUsers()
        if(getItemViewType(position) == ActivityListItemType.DEPARTMENT.value){
            holder.activityView_type.text = ActivityListItemType.DEPARTMENT.name
            holder.activityView_postedBy.text = currentItem.created_by_user_id.toString()
            holder.activityView_postedBy.text = usersViewModel.getUserFromListByID(currentItem.created_by_user_id)
            holder.activityView_postedDate.text = utils.convertLongToTime(currentItem.created_time)
            holder.activityView_contentType.text = getActivityType(currentItem.sub_type)
            if(currentItem.note.isNullOrBlank()){
                holder.activityView_content.text = "No details provided!"
            }else{
                holder.activityView_content.text = currentItem.note
            }

        }
        if(getItemViewType(position) == ActivityListItemType.TASK.value){
            holder.activityView_type.text = ActivityListItemType.TASK.name
            holder.activityView_postedBy.text = currentItem.created_by_user_id.toString()
            holder.activityView_postedBy.text = usersViewModel.getUserFromListByID(currentItem.created_by_user_id)
            holder.activityView_postedDate.text = utils.convertLongToTime(currentItem.created_time)
            holder.activityView_contentType.text = getActivityType(currentItem.sub_type)
            if(currentItem.note.isNullOrBlank()){
                holder.activityView_content.text = "No details provided!"
            }else{
                holder.activityView_content.text = currentItem.note
            }

        }
        if(getItemViewType(position) == ActivityListItemType.ANNOUNCMENT.value){
            holder.activityView_type.text = ActivityListItemType.ANNOUNCMENT.name
            holder.activityView_postedBy.text = currentItem.created_by_user_id.toString()
            holder.activityView_postedBy.text = usersViewModel.getUserFromListByID(currentItem.created_by_user_id)
            holder.activityView_postedDate.text = utils.convertLongToTime(currentItem.created_time)
            holder.activityView_contentType.text = getActivityType(currentItem.sub_type)
            if(currentItem.note.isNullOrBlank()){
                holder.activityView_content.text = "No details provided!"
            }else{
                holder.activityView_content.text = currentItem.note
            }

        }

    }

    override fun getItemCount() = list.size

    // Update the list
    fun setData(newList: ArrayList<ActivitiesResponse>) {
        list = newList
    }

    private enum class ActivityListItemType(val value: Int) {
        DEPARTMENT(1),
        TASK(2),
        ANNOUNCMENT(3)
    }

    companion object {
        fun getActivityType(value: Int) = when (value) {
            1 -> "User Added To Department"
            2 -> "Task Created"
            3 -> "Task Assigned"
            4 -> "Task Status Changed"
            5 -> "Task Progress Change"
            else -> {"Type not defined"}
        }
    }

}