package com.example.quizapp.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.QuestionAddFragmentBinding
import com.example.quizapp.databinding.QuizBodyBinding
import com.example.quizapp.viewModel.QuestionAddModel
import com.example.quizapp.viewModel.UserViewModel


class QuestionAddFragment : Fragment(R.layout.question_add_fragment) {

    private lateinit var binding: QuestionAddFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                AlertDialog.Builder(requireActivity())
                    .setTitle("Close App?")
                    .setMessage("Do you really want to close the app?")
                    .setPositiveButton("YES",
                        DialogInterface.OnClickListener { dialog, which -> getActivity()?.finish();
                            System.exit(0); })
                    .setNegativeButton("NO",
                        DialogInterface.OnClickListener { dialog, which -> }).show()
            }
        })
    }
    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = QuestionAddFragmentBinding.inflate(inflater)

        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model : QuestionAddModel by requireActivity().viewModels()

        binding.questionAdd.setOnClickListener{

            if( binding.questionText.text.isNullOrEmpty() or binding.correctAnswer.text.isNullOrEmpty() or binding.answer2.text.isNullOrEmpty() or binding.answer3.text.isNullOrEmpty() or binding.answer4.text.isNullOrEmpty()){
                val toast = Toast.makeText(getActivity(), "Fill every text fields", Toast.LENGTH_SHORT)
                toast.show();
            }else {
                model.questionAddFlag = 1
                this.findNavController().navigate(R.id.questionListFragment)
            }
        }

    }
}