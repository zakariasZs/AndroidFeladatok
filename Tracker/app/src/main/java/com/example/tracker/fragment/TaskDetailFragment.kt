package com.example.tracker.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.adapter.TasksAdapter
import com.example.tracker.api.ThreeTrackerRepository
import com.example.tracker.api.model.TasksResponse
import com.example.tracker.databinding.TaskDetailScreenBinding
import com.example.tracker.databinding.UserProfileBinding
import com.example.tracker.viewmodel.TasksViewModel
import com.example.tracker.viewmodel.TasksViewModelFactory

class TaskDetailFragment : Fragment(R.layout.task_detail_screen) {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var tasksViewModel: TasksViewModel
    private lateinit var binding: TaskDetailScreenBinding

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
        binding = TaskDetailScreenBinding.inflate(inflater)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val selectedLiveData = MutableLiveData<List<TasksResponse>>(emptyList())
//        selectedLiveData.value = tasksViewModel.tasks.value?.toMutableList()?.apply {
//            get(tasksViewModel.currentItemIndex)
//        }?.toList()
        binding.textView.text = tasksViewModel.currentItemIndex.toString()

    }


}

