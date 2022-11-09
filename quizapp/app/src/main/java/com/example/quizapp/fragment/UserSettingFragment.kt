package com.example.quizapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.UserSettingBinding
import com.example.quizapp.viewModel.UserViewModel

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
        val model : UserViewModel by requireActivity().viewModels()

        val userNameText: TextView= binding.textPersonName
        userNameText.text = model.userName


        binding.settingsButtonSave.setOnClickListener{
            val textField: EditText = binding.textPersonName
            val textFieldText = textField.text
            if(textFieldText.toString().length == 0) {
                val toast = Toast.makeText(getActivity(), "No Username set", Toast.LENGTH_SHORT)
                toast.show();
            }
            else {
                val toast = Toast.makeText(getActivity(), "New Username is set", Toast.LENGTH_SHORT)
                toast.show();
                model.userName = textFieldText.toString()
            }

        }

    }
}