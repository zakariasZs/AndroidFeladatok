package com.example.a3track.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.a3track.MainActivity
import com.example.a3track.R
import com.example.a3track.databinding.SplashScreenBinding

class SplashScreenFragment : Fragment(R.layout.splash_screen) {

    private lateinit var binding: SplashScreenBinding
    protected open var bottomNavigationViewVisibility = View.GONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if (activity is MainActivity) {
//            var  mainActivity = activity as MainActivity
//            mainActivity.setBottomNavigationVisibility(bottomNavigationViewVisibility)
//        }

    }
    override fun onCreateView (
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
        logo.animate().setDuration(1500).alpha(1f).withEndAction{
            this.findNavController().navigate(R.id.loginFragment)
        }

    }
}