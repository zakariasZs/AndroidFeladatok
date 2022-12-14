package com.example.tracker.fragment

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
import com.example.tracker.adapter.MyGroupsAdapter
import com.example.tracker.adapter.TasksAdapter
import com.example.tracker.api.ThreeTrackerRepository
import com.example.tracker.api.model.MyGroupsResponse
import com.example.tracker.viewmodel.*

class MyGroupsFragment: Fragment(R.layout.my_groups_screen), MyGroupsAdapter.OnItemClickListener {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var myGroupsViewModel: MyGroupsViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var myGroupAdapter: MyGroupsAdapter
    private lateinit var getUsersViewModel: GetUsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = MyGroupsViewModelFactory(ThreeTrackerRepository())
        myGroupsViewModel = ViewModelProvider(requireActivity(), factory)[MyGroupsViewModel::class.java]

        val factoryUsers = GetUsersViewModelFactory(ThreeTrackerRepository())
        getUsersViewModel = ViewModelProvider(requireActivity(), factoryUsers)[GetUsersViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.my_groups_screen, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        setupRecyclerView()
        myGroupsViewModel.groups.observe(viewLifecycleOwner) {
            myGroupAdapter.setData(myGroupsViewModel.groups.value as ArrayList<MyGroupsResponse>)
            myGroupAdapter.notifyDataSetChanged()
            Log.d(TAG, "Tasks list = $it")
        }

        return view
    }

    private fun setupRecyclerView() {
        myGroupAdapter = MyGroupsAdapter(ArrayList(), this.requireContext(), this)
        recyclerView.adapter = myGroupAdapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
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

    }

    override fun onItemClick(position: Int) {
        myGroupsViewModel.groupToShow = myGroupsViewModel.groups.value?.get(position)!!
        Log.e("XXX- item clicked: ",myGroupsViewModel.groupToShow.toString())

        this.findNavController().navigate(R.id.myGroupMembersFragment)
    }


}