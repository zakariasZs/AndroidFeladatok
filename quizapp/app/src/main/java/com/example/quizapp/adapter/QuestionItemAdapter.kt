package com.example.quizapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.fragment.Question_Item


class QuestionItemAdapter(private val questionList: List<Question_Item>) :
    RecyclerView.Adapter<QuestionItemAdapter.QuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.question_item,
            parent, false)

        return QuestionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val currentItem = questionList[position]

        holder.questionText.text = currentItem.question
        holder.answerText.text = currentItem.answer


    }

    override fun getItemCount() = questionList.size

    class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionText: TextView = itemView.findViewById(R.id.question_text)
        val answerText : TextView = itemView.findViewById(R.id.answer_text)




    }
}