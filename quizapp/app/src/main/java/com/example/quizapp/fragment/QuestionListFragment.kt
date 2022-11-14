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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.adapter.QuestionItemAdapter
import com.example.quizapp.databinding.QuestionListBinding
import com.example.quizapp.databinding.QuizStartBinding
import com.example.quizapp.viewModel.QuestionAddModel
import com.example.quizapp.viewModel.UserViewModel
import com.google.android.material.snackbar.Snackbar

class QuestionListFragment : Fragment(R.layout.question_list) {

    private lateinit var binding: QuestionListBinding
    private var adapter: RecyclerView.Adapter<QuestionItemAdapter.QuestionViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = QuestionListBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model : UserViewModel by requireActivity().viewModels()
        val modelQuestionAdd : QuestionAddModel by requireActivity().viewModels()

        val dummyData = generateDummyList(100)

        val recyclerViewQuestion = binding.recyclerView
        recyclerViewQuestion.adapter = QuestionItemAdapter(dummyData)
        recyclerViewQuestion.layoutManager = LinearLayoutManager(getActivity())
        recyclerViewQuestion.setHasFixedSize(true)


        binding.addQuestion.setOnClickListener { view ->
            this.findNavController().navigate(R.id.questionAddFragment)
        }

    }

    private fun generateDummyList(size: Int): List<Question_Item> {

        val list = ArrayList<Question_Item>()

        for (i in 0 until size) {
            val item = Question_Item("Question $i", "Answer $i")
            list += item
        }

        return list
    }


}