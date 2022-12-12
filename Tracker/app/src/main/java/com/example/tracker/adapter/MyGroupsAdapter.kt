package com.example.tracker.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.api.model.MyGroupsResponse

class MyGroupsAdapter (
    private var list: ArrayList<MyGroupsResponse>,
    private val context: Context
) :
    RecyclerView.Adapter<MyGroupsAdapter.DataViewHolder>() {

    var currentItemId: Int = -1

    // 1. user defined ViewHolder type - Embedded class!
    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val myGroupView_name: TextView = itemView.findViewById(R.id.group_name)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val currentPosition = this.adapterPosition
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    // 2. Called only a few times = number of items on screen + a few more ones
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.group_item, parent, false)
        return DataViewHolder(itemView)
    }


    // 3. Called many times, when we scroll the list
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = list[position]

        holder.myGroupView_name.text = currentItem.name

        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.tasks_item_anim)

//        Glide.with(this.context)
//            .load(R.drawable.ic_user)
//            .override(200, 200)
//            .into(holder.imageView);

    }

    override fun getItemCount() = list.size

    // Update the list
    fun setData(newList: ArrayList<MyGroupsResponse>) {
        list = newList
    }
}


