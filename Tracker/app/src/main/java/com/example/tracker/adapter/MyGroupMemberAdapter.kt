package com.example.tracker.adapter

import android.content.Context
import android.graphics.Color
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
import com.example.tracker.api.model.GetUserResponse
import com.example.tracker.api.model.MyGroupsResponse
import com.example.tracker.api.model.TasksResponse
import com.example.tracker.viewmodel.MyGroupsViewModel
import com.example.tracker.util.StringUtil.Companion as utils
import com.example.tracker.util.TaskUtil as utilsTask


class MyGroupMemberAdapter(
    private var list: ArrayList<GetUserResponse>,
    private val context: Context,
    private val myGroupMember: MyGroupsViewModel
) :
    RecyclerView.Adapter<MyGroupMemberAdapter.DataViewHolder>() {


    // 1. user defined ViewHolder type - Embedded class!
    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val groupMember_name: TextView = itemView.findViewById(R.id.memberName)


    }

    // 2. Called only a few times = number of items on screen + a few more ones
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.my_group_member_card, parent, false)
        return DataViewHolder(itemView)
    }




    // 3. Called many times, when we scroll the list
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = list[position]

        if(myGroupMember.groupToShow.id == currentItem.department_id){

            holder.groupMember_name.text = currentItem.first_name + " " + currentItem.last_name
            holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.tasks_item_anim)
        }

    }

    override fun getItemCount() = list.size

    // Update the list
    fun setData(newList: ArrayList<GetUserResponse>) {
        list = newList
    }


}