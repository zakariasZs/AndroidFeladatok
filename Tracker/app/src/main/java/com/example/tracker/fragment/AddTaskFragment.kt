package com.example.tracker.fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Spinner
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.adapter.TasksAdapter
import com.example.tracker.adapter.UsersSpinnerAdapter
import com.example.tracker.api.ThreeTrackerRepository
import com.example.tracker.api.model.GetUserResponse
import com.example.tracker.api.model.TasksResponse
import com.example.tracker.databinding.AddTaskScreenBinding
import com.example.tracker.databinding.SplashScreenBinding
import com.example.tracker.viewmodel.GetUsersViewModel
import com.example.tracker.viewmodel.GetUsersViewModelFactory
import com.example.tracker.viewmodel.TasksViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddTaskFragment : Fragment(R.layout.add_task_screen), DatePickerDialog.OnDateSetListener {


    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var binding: AddTaskScreenBinding
    private val calendar = Calendar.getInstance()
    private val formatter = SimpleDateFormat("yyyy.MM.dd", Locale.ROOT)
    private lateinit var getUsersViewModel: GetUsersViewModel
    private lateinit var usersSpinnerAdapter: UsersSpinnerAdapter
    private lateinit var userSpiner : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = GetUsersViewModelFactory(ThreeTrackerRepository())
        getUsersViewModel = ViewModelProvider(this, factory)[GetUsersViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.add_task_screen, container, false)
        userSpiner = view.findViewById(R.id.selectAssignee)
        setupSpinnerView()
        getUsersViewModel.users.observe(viewLifecycleOwner) {
            usersSpinnerAdapter.setData(getUsersViewModel.users.value as ArrayList<GetUserResponse>)
            usersSpinnerAdapter.notifyDataSetChanged()
            Log.d(AddTaskFragment.TAG, "Tasks list = $it")
        }

        binding = AddTaskScreenBinding.inflate(inflater)

        return view
    }

    private fun setupSpinnerView() {
        usersSpinnerAdapter = UsersSpinnerAdapter(ArrayList(), this.requireContext())
        userSpiner.adapter = usersSpinnerAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUsersViewModel.getUsers()

        binding.deadLinePicker.setOnClickListener{
            DatePickerDialog( view.context,
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.cancelCreation.setOnClickListener() {
            AlertDialog.Builder(requireActivity())
                .setTitle("Close App?")
                .setMessage("Do you really want to cancel the task creation?")
                .setPositiveButton("YES",
                    DialogInterface.OnClickListener { dialog, which -> this.findNavController().navigate(R.id.tasksFragment);})
                .setNegativeButton("NO",
                    DialogInterface.OnClickListener { dialog, which -> }).show()
        }

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