package com.example.tracker.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.adapter.ActivitiesAdapter
import com.example.tracker.api.ThreeTrackerRepository
import com.example.tracker.api.model.ActivitiesResponse
import com.example.tracker.viewmodel.*

class ActivityFragment : Fragment(R.layout.my_activities_screen){

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var activitiesViewModel: ActivitiesViewModel
    private lateinit var usersViewModel: GetUsersViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var activitiesAdapter: ActivitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ActivitiesViewModelFactory(ThreeTrackerRepository())
        activitiesViewModel = ViewModelProvider(requireActivity(), factory)[ActivitiesViewModel::class.java]
        val factoryUsers = GetUsersViewModelFactory(ThreeTrackerRepository())
        usersViewModel = ViewModelProvider(requireActivity(), factoryUsers)[GetUsersViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.my_activities_screen, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        setupRecyclerView()
        activitiesViewModel.activities.observe(viewLifecycleOwner) {
            activitiesAdapter.setData(activitiesViewModel.activities.value as ArrayList<ActivitiesResponse>)
            activitiesAdapter.notifyDataSetChanged()
        }

        return view
    }


    private fun setupRecyclerView() {
        usersViewModel.getUsers()
        activitiesAdapter = ActivitiesAdapter(ArrayList(), this.requireContext(), usersViewModel)
        recyclerView.adapter = activitiesAdapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        recyclerView.setHasFixedSize(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activitiesViewModel.getActivities()



    }
}