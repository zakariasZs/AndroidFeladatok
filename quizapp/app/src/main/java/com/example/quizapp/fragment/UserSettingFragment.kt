package com.example.quizapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quizapp.R
import com.example.quizapp.databinding.UserSettingBinding

class UserSettingFragment : Fragment(R.layout.user_setting) {

    private lateinit var binding: UserSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = UserSettingBinding.inflate(inflater)

        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



//        binding.restartQuiz.setOnClickListener{
//            this.findNavController().navigate(R.id.quizStartFragment)
//
//        }

    }
}