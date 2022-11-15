package com.example.quizapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.QuizBodyBinding
import com.example.quizapp.databinding.QuizEndBinding
import com.example.quizapp.viewModel.UserViewModel

class QuizEndFragment : Fragment(R.layout.quiz_end) {

    private lateinit var binding: QuizEndBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = QuizEndBinding.inflate(inflater)

        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model : UserViewModel by requireActivity().viewModels()
        val resultNrText: TextView = binding.questionResult
        resultNrText.text = model.nrOfCorrectAnswers.toString().plus( "/").plus(model.nrOfQuestions.toString())

        binding.restartQuiz.setOnClickListener{
            model.nrOfCorrectAnswers = 0
            this.findNavController().navigate(R.id.quizStartFragment)

        }

    }
}