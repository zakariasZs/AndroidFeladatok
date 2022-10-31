package com.example.quizapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.QuizBodyBinding
import com.example.quizapp.databinding.QuizStartBinding
import com.example.quizapp.viewModel.UserViewModel
import com.google.android.material.snackbar.Snackbar

class QuizFragment :Fragment(R.layout.quiz_body) {

    private lateinit var binding: QuizBodyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = QuizBodyBinding.inflate(inflater)

        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model : UserViewModel by requireActivity().viewModels()
        val questionText: TextView = binding.questionText
        questionText.text = model.userName

        binding.nextQuestion.setOnClickListener{
            this.findNavController().navigate(R.id.quizEndFragment)


        }

    }
}