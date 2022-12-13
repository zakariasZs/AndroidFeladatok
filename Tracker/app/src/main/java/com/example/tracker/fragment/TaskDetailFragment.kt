package com.example.tracker.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tracker.R
import com.example.tracker.api.model.TasksResponse
import com.example.tracker.databinding.TaskDetailScreenBinding
import com.example.tracker.viewmodel.TasksViewModel
import com.example.tracker.util.TaskUtil as utilsTask
import com.example.tracker.util.StringUtil.Companion as utils

class TaskDetailFragment : Fragment(R.layout.task_detail_screen) {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var binding: TaskDetailScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = TaskDetailScreenBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tasksViewModel : TasksViewModel by requireActivity().viewModels()

        if(tasksViewModel.taskToShowID.id == -1){
            this.findNavController().navigate(R.id.tasksFragment)
        }
        Log.e("XXX- selected item info ", tasksViewModel.taskToShowID.toString())
        binding.taskName.text = tasksViewModel.taskToShowID.title
        binding.taskDepartment.text = tasksViewModel.taskToShowID.departmentID.toString()
        binding.taskPriority.text = utilsTask.ItemPriority.values().get(tasksViewModel.taskToShowID.priority).toString()
        binding.taskdeadline.text = utils.convertLongToTime(tasksViewModel.taskToShowID.deadline)
        binding.taskDetail.text = tasksViewModel.taskToShowID.description

        binding.backToTasks.setOnClickListener {
            tasksViewModel.taskToShowID = TasksResponse(-1, "", "", -1, -1, -1, -1, -1, -1, -1, "null")
            this.findNavController().navigate(R.id.tasksFragment)
        }

    }


}

