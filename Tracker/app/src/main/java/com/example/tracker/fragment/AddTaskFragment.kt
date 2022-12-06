package com.example.tracker.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.databinding.AddTaskScreenBinding
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class AddTaskFragment : Fragment(R.layout.add_task_screen), DatePickerDialog.OnDateSetListener {

    private lateinit var binding: AddTaskScreenBinding
    private val calendar = Calendar.getInstance()
    private val formatter = SimpleDateFormat("yyyy.MM.dd", Locale.ROOT)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddTaskScreenBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.deadLinePicker.setOnClickListener{
            DatePickerDialog( view.context,
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

//        binding.addQuestion.setOnClickListener { view ->
//            this.findNavController().navigate(R.id.questionAddFragment)
//        }

    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        Log.e("XXX- Calendar: ", "$year -- $month -- $dayOfMonth")
        calendar.set(year, month, dayOfMonth)
        displayFormatedDate(calendar.timeInMillis)
    }

    private fun displayFormatedDate( timestamp: Long){
        binding.deadLinePicker.text = formatter.format(timestamp)
    }

}