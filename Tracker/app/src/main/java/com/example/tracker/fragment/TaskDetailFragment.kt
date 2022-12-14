package com.example.tracker.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tracker.R
import com.example.tracker.api.ThreeTrackerRepository
import com.example.tracker.api.model.GetUserResponse
import com.example.tracker.api.model.TasksResponse
import com.example.tracker.databinding.TaskDetailScreenBinding
import com.example.tracker.viewmodel.GetUsersViewModel
import com.example.tracker.viewmodel.GetUsersViewModelFactory
import com.example.tracker.viewmodel.TasksViewModel
import java.util.ArrayList
import com.example.tracker.util.TaskUtil as utilsTask
import com.example.tracker.util.StringUtil.Companion as utils

class TaskDetailFragment : Fragment(R.layout.task_detail_screen) {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var binding: TaskDetailScreenBinding
    private lateinit var getUsersViewModel: GetUsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factoryUsers = GetUsersViewModelFactory(ThreeTrackerRepository())
        getUsersViewModel = ViewModelProvider(this, factoryUsers)[GetUsersViewModel::class.java]

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

        if(tasksViewModel.taskToShow.id == -1){
            this.findNavController().navigate(R.id.tasksFragment)
        }

        getUsersViewModel.getUsers()

        Log.e("XXX- selected item info ", tasksViewModel.taskToShow.toString())
        binding.taskName.text = tasksViewModel.taskToShow.title
        val creatorId =  tasksViewModel.taskToShow.createdByUserID
        binding.taskCreatorName.text = getUsersViewModel.getUserFromListByID(creatorId)
        binding.taskDepartment.text = tasksViewModel.taskToShow.departmentID.toString()
        binding.taskPriority.text = utilsTask.ItemPriority.values().get(tasksViewModel.taskToShow.priority).toString()
        binding.taskdeadline.text = utils.convertLongToTime(tasksViewModel.taskToShow.deadline)
        binding.taskDetail.text = tasksViewModel.taskToShow.description

        binding.backToTasks.setOnClickListener {
            tasksViewModel.taskToShow = TasksResponse(-1, "", "", -1, -1, -1, -1, -1, -1, -1, "null")
            this.findNavController().navigate(R.id.tasksFragment)
        }

    }


}

