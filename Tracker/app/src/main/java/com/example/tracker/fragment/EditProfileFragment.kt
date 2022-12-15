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
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tracker.R
import com.example.tracker.api.ThreeTrackerRepository
import com.example.tracker.databinding.UserProfileBinding
import com.example.tracker.databinding.UserProfileEditBinding
import com.example.tracker.viewmodel.*

class EditProfileFragment : Fragment(R.layout.user_profile_edit) {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var userProfileViewModel: UserProfileViewModel
    private lateinit var updateProfileViewModel: UpdateProfileViewModel
    private lateinit var binding: UserProfileEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = UserProfileViewModelFactory(ThreeTrackerRepository())
        userProfileViewModel = ViewModelProvider(this, factory)[UserProfileViewModel::class.java]

        val updateProfileViewModelFactory = UpdateProfileViewModelFactory(ThreeTrackerRepository())
        updateProfileViewModel = ViewModelProvider(this, updateProfileViewModelFactory)[UpdateProfileViewModel::class.java]

    }
    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = UserProfileEditBinding.inflate(inflater)

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
            }else {

                binding.editLastName.setText(userProfileViewModel.last_name.value)
                binding.editFirstName.setText(userProfileViewModel.first_name.value)
                binding.editPhoneNumber.setText(userProfileViewModel.phone_number.value)
                binding.editLocation.setText(userProfileViewModel.location.value)

                binding.saveProfile.setOnClickListener {

                    val lastName = binding.editLastName.text.toString()
                    val firstName = binding.editFirstName.text.toString()
                    val location = binding.editLocation.text.toString()
                    val phoneNumber = binding.editPhoneNumber.text.toString()
                    val imageUrl = ""

                    if(phoneNumber.length != 10){
                        val toast = Toast.makeText(getActivity(), "Phone Number form incorrect", Toast.LENGTH_SHORT)
                        toast.show();
                    }else{
                        updateProfileViewModel.updateProfile(lastName, firstName, location, phoneNumber, imageUrl)

                        updateProfileViewModel.isSuccessful.observe(this.viewLifecycleOwner) {
                            Log.d(EditProfileFragment.TAG, "Update profile successfully = $it")
                            if (it) {

                                Log.e("XXX- Task Post ", updateProfileViewModel.isSuccessful.toString())
                                val toast = Toast.makeText(getActivity(), "User Profile Updated", Toast.LENGTH_SHORT)
                                toast.show();
                                this.findNavController().navigate(R.id.userProfileFragment)
                            }else{
                                val toast = Toast.makeText(getActivity(), "Could not update profile", Toast.LENGTH_SHORT)
                                toast.show();
                            }
                        }
                    }






                }

            }
        }



    }


}

