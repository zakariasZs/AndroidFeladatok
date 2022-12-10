package com.example.tracker.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.adapter.TasksAdapter
import com.example.tracker.api.ThreeTrackerRepository
import com.example.tracker.api.model.TasksResponse
import com.example.tracker.viewmodel.TasksViewModel
import com.example.tracker.viewmodel.TasksViewModelFactory

class TasksFragment : Fragment(R.layout.task_list), TasksAdapter.OnItemClickListener, TasksAdapter.OnItemLongClickListener{

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var tasksViewModel: TasksViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskadapter: TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = TasksViewModelFactory(ThreeTrackerRepository())
        tasksViewModel = ViewModelProvider(this, factory)[TasksViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.task_list, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        setupRecyclerView()
        tasksViewModel.tasks.observe(viewLifecycleOwner) {
            taskadapter.setData(tasksViewModel.tasks.value as ArrayList<TasksResponse>)
            taskadapter.notifyDataSetChanged()
            Log.d(TAG, "Tasks list = $it")
        }

        return view
    }

    private fun setupRecyclerView() {
        taskadapter = TasksAdapter(ArrayList(), this.requireContext(), this, this)
        recyclerView.adapter = taskadapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        taskadapter.onItemClicked
//        recyclerView.addItemDecoration(
//            DividerItemDecoration(
//                activity,
//                DividerItemDecoration.VERTICAL
//            )
//        )
        recyclerView.setHasFixedSize(true)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val addtaskButton = view.findViewById<ImageButton>(R.id.addTask)

        addtaskButton.setOnClickListener{
            this.findNavController().navigate(R.id.addTaskFragment)
        }


    }

    override fun onItemClick(position: Int) {
        Log.e("XXX- item clicked: ",position.toString())
        this.findNavController().navigate(R.id.taskDetailFragment)
    }

    override fun onItemLongClick(position: Int) {
//        TODO("Not yet implemented")
    }
}