package com.example.tracker.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tracker.R
import com.example.tracker.api.ThreeTrackerRepository
import com.example.tracker.databinding.UserProfileBinding
import com.example.tracker.viewmodel.TasksViewModel
import com.example.tracker.viewmodel.UserProfileViewModel
import com.example.tracker.viewmodel.UserProfileViewModelFactory

class UserProfileFragment : Fragment(R.layout.user_profile) {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var userProfileViewModel: UserProfileViewModel
    private lateinit var binding: UserProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = UserProfileViewModelFactory(ThreeTrackerRepository())
        userProfileViewModel = ViewModelProvider(this, factory)[UserProfileViewModel::class.java]
    }
    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = UserProfileBinding.inflate(inflater)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userProfileViewModel.getUserProfile()
        userProfileViewModel.isSuccessful.observe(this.viewLifecycleOwner){
            Log.e("XXX-check token result: ", it.toString())
            if (!it)
            {
                this.findNavController().navigate(R.id.loginFragment)
            }
            binding.userName.text = userProfileViewModel.last_name.value.toString() + " " + userProfileViewModel.first_name.value.toString()
            binding.emailAddress.text = userProfileViewModel.email.value.toString()
            binding.phoneNumber.text = userProfileViewModel.phone_number.value.toString()
            binding.officeLocation.text = userProfileViewModel.location.value.toString()

        }




        binding.groupsButton.setOnClickListener {
            val toast = Toast.makeText(getActivity(), "Display groups", Toast.LENGTH_SHORT)
            toast.show();
        }

        binding.editProfileButton.setOnClickListener {
            val toast = Toast.makeText(getActivity(), "Edit profile", Toast.LENGTH_SHORT)
            toast.show();
        }

    }


}