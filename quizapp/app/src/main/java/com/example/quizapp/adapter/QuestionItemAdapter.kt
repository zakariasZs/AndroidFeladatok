package com.example.quizapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.fragment.Question_Item


class QuestionItemAdapter(
    private val questionList: List<Question_Item>,
    private val listener: OnItemClickListener
) :
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

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val questionText: TextView = itemView.findViewById(R.id.question_text)
        val answerText : TextView = itemView.findViewById(R.id.answer_text)

        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position);
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}