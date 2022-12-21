package com.example.tracker.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.adapter.MyGroupMemberAdapter
import com.example.tracker.adapter.TasksAdapter
import com.example.tracker.api.ThreeTrackerRepository
import com.example.tracker.api.model.GetUserResponse
import com.example.tracker.api.model.TasksResponse
import com.example.tracker.databinding.MyGroupMembersBinding
import com.example.tracker.viewmodel.GetUsersViewModel
import com.example.tracker.viewmodel.GetUsersViewModelFactory
import com.example.tracker.viewmodel.MyGroupsViewModel
import com.example.tracker.viewmodel.MyGroupsViewModelFactory

class MyGroupMembersFragment : Fragment(R.layout.my_group_members) {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var myGroupsViewModel: MyGroupsViewModel
    private lateinit var binding: MyGroupMembersBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var getUsersViewModel: GetUsersViewModel
    private lateinit var myGroupMemberAdapter: MyGroupMemberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = MyGroupsViewModelFactory(ThreeTrackerRepository())
        myGroupsViewModel = ViewModelProvider(requireActivity(), factory)[MyGroupsViewModel::class.java]

        val factoryUsers = GetUsersViewModelFactory(ThreeTrackerRepository())
        getUsersViewModel = ViewModelProvider(requireActivity(), factoryUsers)[GetUsersViewModel::class.java]
    }
    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.my_group_members, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        setupRecyclerView()
        getUsersViewModel.users.observe(viewLifecycleOwner) {
            myGroupMemberAdapter.setData(getUsersViewModel.users.value as ArrayList<GetUserResponse>)
            myGroupMemberAdapter.notifyDataSetChanged()
            Log.d(MyGroupMembersFragment.TAG, "Tasks list = $it")
        }

        return view

    }

    private fun setupRecyclerView() {
        myGroupMemberAdapter = MyGroupMemberAdapter(ArrayList(), this.requireContext(), myGroupsViewModel)
        recyclerView.adapter = myGroupMemberAdapter
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
        if(myGroupsViewModel.groupToShow.id != -1){
            getUsersViewModel.getUsers()
            Log.e("XXX- selected group ", myGroupsViewModel.groupToShow.id.toString())

            view.findViewById<TextView>(R.id.groupName).text = myGroupsViewModel.groupToShow.name

            view.findViewById<ImageView>(R.id.imageView).setOnClickListener {
                this.findNavController().navigate(R.id.myGroupsFragment)
            }


        }else{
            this.findNavController().navigate(R.id.myGroupsFragment)
        }




    }


}