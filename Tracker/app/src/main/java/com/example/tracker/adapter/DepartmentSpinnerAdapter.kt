package com.example.tracker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.tracker.R
import com.example.tracker.api.model.GetDepartmentResponse

class DepartmentSpinnerAdapter(
    private var list: ArrayList<GetDepartmentResponse>,
    private val context: Context
): BaseAdapter() {


    val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return list[p0];
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong();
    }

    override fun getView(p0: Int, p1: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ItemRowHolder
        if (p1 == null) {
            view = inflater.inflate(R.layout.departments_spinner, parent, false)
            vh = ItemRowHolder(view)
            view?.tag = vh
        } else {
            view = p1
            vh = view.tag as ItemRowHolder
        }

        val currentItem = list[p0]
        vh.department_name.text = currentItem.department_name
        return view
    }

    fun setData(newList: ArrayList<GetDepartmentResponse>) {
        list = newList
    }

    private class ItemRowHolder(row: View?) {
        val department_name: TextView
        init {
            this.department_name = row?.findViewById(R.id.spinner_departments) as TextView
        }
    }


}