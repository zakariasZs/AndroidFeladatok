package com.example.quizapp.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.QuizBodyBinding


class QuizFragment :Fragment(R.layout.quiz_body) {

    private lateinit var binding: QuizBodyBinding

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
        binding = QuizBodyBinding.inflate(inflater)

        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val model : UserViewModel by requireActivity().viewModels()
//
//        val questionText:TextView =binding.questionText
//        val radioGroup: RadioGroup =binding.radioGroup
//        val answer1Rb:RadioButton=binding.radioButton
//        val answer2Rb:RadioButton=binding.radioButton2
//        val answer3Rb:RadioButton=binding.radioButton3
//        val answer4Rb:RadioButton=binding.radioButton4
//        val nextBtn:Button=binding.nextQuestion
//
//        val nrOfQuestions=3
//
//        val itemServiceTemp = ItemService(ItemRepository)
//        val items : List<Item> = itemServiceTemp.selectRandomItems(nrOfQuestions)
//
//        val itemController= ItemController(itemServiceTemp,requireActivity(),questionText,arrayListOf(answer1Rb,answer2Rb,answer3Rb,answer4Rb),nextBtn,radioGroup)
//        itemController.quiz(items)
//
//        model.nrOfQuestions=nrOfQuestions

        binding.nextQuestion.setOnClickListener{
            this.findNavController().navigate(R.id.quizEndFragment)

        }

    }
}