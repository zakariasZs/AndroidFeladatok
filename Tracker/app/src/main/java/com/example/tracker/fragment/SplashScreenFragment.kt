package com.example.tracker.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tracker.App
import com.example.tracker.R
import com.example.tracker.api.ThreeTrackerRepository
import com.example.tracker.databinding.SplashScreenBinding
import com.example.tracker.manager.SharedPreferencesManager
import com.example.tracker.viewmodel.LoginViewModel
import com.example.tracker.viewmodel.LoginViewModelFactory
import com.example.tracker.viewmodel.UserProfileViewModel
import com.example.tracker.viewmodel.UserProfileViewModelFactory

class SplashScreenFragment : Fragment(R.layout.splash_screen) {

    private lateinit var binding: SplashScreenBinding
    private lateinit var userProfileViewModel: UserProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = UserProfileViewModelFactory(ThreeTrackerRepository())
        userProfileViewModel = ViewModelProvider(this, factory)[UserProfileViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //with binding
        binding = SplashScreenBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val logo: ImageView = binding.threeLogo
        logo.alpha = 0f
        logo.animate().setDuration(1500).alpha(1f).withEndAction {

            userProfileViewModel.getUserProfile()
            userProfileViewModel.isSuccessful.observe(this.viewLifecycleOwner){
                Log.e("XXX-check token result: ", it.toString())
                if (it) {
                    this.findNavController().navigate(R.id.tasksFragment)
                }else{
                    this.findNavController().navigate(R.id.loginFragment)
                }

            }

        }

    }
}