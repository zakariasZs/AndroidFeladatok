package com.example.quizapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.adapter.QuestionItemAdapter
import com.example.quizapp.databinding.QuestionListBinding
import com.example.quizapp.viewModel.QuestionListModel
import com.example.quizapp.viewModel.UserViewModel

class QuestionListFragment : Fragment(R.layout.question_list), QuestionItemAdapter.OnItemClickListener {

    private lateinit var binding: QuestionListBinding
    private var adapter: RecyclerView.Adapter<QuestionItemAdapter.QuestionViewHolder>? = null
    private lateinit var questions :List<Question_Item>

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
        val modelQuestion : QuestionListModel by requireActivity().viewModels()

        questions = getQuestionList(modelQuestion.questionList)

        val recyclerViewQuestion = binding.recyclerView
        recyclerViewQuestion.adapter = QuestionItemAdapter(questions)
        recyclerViewQuestion.layoutManager = LinearLayoutManager(getActivity())
        recyclerViewQuestion.setHasFixedSize(true)


        binding.addQuestion.setOnClickListener { view ->
            this.findNavController().navigate(R.id.questionAddFragment)
        }

    }

//    private fun generateDummyList(size: Int): List<Question_Item> {
//
//        val list = ArrayList<Question_Item>()
//
//        for (i in 0 until size) {
//            val item = Question_Item("Question $i", "Answer $i")
//            list += item
//        }
//
//        return list
//    }

    override fun onItemClick(position: Int) {
        val clickedItem: Question_Item = questions[position]
    }



    private fun getQuestionList(questionList: List<String>): List<Question_Item> {
        var currentQuestion = 0
        val list = ArrayList<Question_Item>()
        val size = questionList.size-6

        for (i in 0..size step 6) {

            val item = Question_Item(questionList[i], questionList[i+questionList[i+5].toInt()])
            list += item
        }

        return list
    }


}