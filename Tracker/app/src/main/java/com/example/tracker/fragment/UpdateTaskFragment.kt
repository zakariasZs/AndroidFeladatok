package com.example.tracker.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tracker.R
import com.example.tracker.adapter.DepartmentSpinnerAdapter
import com.example.tracker.adapter.UsersSpinnerAdapter
import com.example.tracker.api.ThreeTrackerRepository
import com.example.tracker.api.model.GetDepartmentResponse
import com.example.tracker.api.model.GetUserResponse
import com.example.tracker.databinding.UpdateTaskScreenBinding
import com.example.tracker.viewmodel.*
import java.text.SimpleDateFormat
import java.util.*
import com.example.tracker.util.StringUtil as utils

class UpdateTaskFragment : Fragment(R.layout.update_task_screen), DatePickerDialog.OnDateSetListener  {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var binding: UpdateTaskScreenBinding
    private lateinit var updateTaskViewModel :UpdateTaskViewModel

    private val calendar = Calendar.getInstance()
    private val formatter = SimpleDateFormat("yyyy.MM.dd", Locale.ROOT)
    //for API callback viewmodel, adapeter
    private lateinit var getUsersViewModel: GetUsersViewModel
    private lateinit var usersSpinnerAdapter: UsersSpinnerAdapter
    private lateinit var userSpiner : Spinner

    private lateinit var getDepartmentViewModel: GetDepartmentViewModel
    private lateinit var departmentSpinnerAdapter: DepartmentSpinnerAdapter
    private lateinit var departmentSpiner : Spinner

    private lateinit var prioritytSpiner : Spinner
    private lateinit var statustSpiner : Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factoryUsers = GetUsersViewModelFactory(ThreeTrackerRepository())
        getUsersViewModel = ViewModelProvider(requireActivity(), factoryUsers)[GetUsersViewModel::class.java]

        val factoryDepartments = GetDepartmentViewModelFactory(ThreeTrackerRepository())
        getDepartmentViewModel = ViewModelProvider(requireActivity(), factoryDepartments)[GetDepartmentViewModel::class.java]

        val updateTaskPostViewModelFactory = UpdateTaskViewModelFactory(ThreeTrackerRepository())
        updateTaskViewModel = ViewModelProvider(requireActivity(), updateTaskPostViewModelFactory)[UpdateTaskViewModel::class.java]

    }
    override fun onCreateView (
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
        val view = inflater.inflate(R.layout.update_task_screen, container, false)
        userSpiner = view.findViewById(R.id.selectAssignee)
        setupUserSpinnerView()
        getUsersViewModel.users.observe(viewLifecycleOwner) {
            usersSpinnerAdapter.setData(getUsersViewModel.users.value as ArrayList<GetUserResponse>)
            usersSpinnerAdapter.notifyDataSetChanged()
            Log.d(UpdateTaskFragment.TAG, "Users list = $it")
        }

        departmentSpiner = view.findViewById(R.id.selectDepartment)
        setupDepartmentSpinnerView()
        getDepartmentViewModel.departments.observe(viewLifecycleOwner) {
            departmentSpinnerAdapter.setData(getDepartmentViewModel.departments.value as ArrayList<GetDepartmentResponse>)
            departmentSpinnerAdapter.notifyDataSetChanged()
            Log.d(UpdateTaskFragment.TAG, "Departments list = $it")
        }

        prioritytSpiner = view.findViewById(R.id.selectPriority)
        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapterPriority = ArrayAdapter.createFromResource(view.context,R.array.priority_array, android.R.layout.simple_spinner_item)
        // Specify the layout to use when the list of choices appears
        adapterPriority.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        prioritytSpiner.adapter = adapterPriority

        statustSpiner = view.findViewById(R.id.selectStatus)
        val adapterStatus = ArrayAdapter.createFromResource(view.context,R.array.status_array, android.R.layout.simple_spinner_item)
        adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        statustSpiner.adapter = adapterStatus

        // Inflate the layout for this fragment
        binding = UpdateTaskScreenBinding.inflate(inflater)

        return view
    }

    private fun setupUserSpinnerView() {
        usersSpinnerAdapter = UsersSpinnerAdapter(ArrayList(), this.requireContext())
        userSpiner.adapter = usersSpinnerAdapter

    }

    private fun setupDepartmentSpinnerView() {
        departmentSpinnerAdapter = DepartmentSpinnerAdapter(ArrayList(), this.requireContext())
        departmentSpiner.adapter = departmentSpinnerAdapter
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tasksViewModel : TasksViewModel by requireActivity().viewModels()

        view.findViewById<TextView>(R.id.deadLinePicker).setOnClickListener{
            DatePickerDialog( view.context,
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        view.findViewById<ImageButton>(R.id.cancelCreation).setOnClickListener{
            AlertDialog.Builder(requireActivity())
                .setTitle("Close App?")
                .setMessage("Do you really want to cancel the task update?")
                .setPositiveButton("YES",
                    DialogInterface.OnClickListener { dialog, which -> this.findNavController().navigate(R.id.taskDetailFragment);})
                .setNegativeButton("NO",
                    DialogInterface.OnClickListener { dialog, which -> }).show()
        }


        if(tasksViewModel.taskToShow.id == -1){
            this.findNavController().navigate(R.id.tasksFragment)
        }else{
            Log.e("XXX- selected item info ", tasksViewModel.taskToShow.toString())

            val taskName = view.findViewById<EditText>(R.id.taskName)
            taskName.setText(tasksViewModel.taskToShow.title)

            val taskAssignee = view.findViewById<Spinner>(R.id.selectAssignee)
            val taskAssigneePosition = getUsersViewModel.getUserPositionInListById(tasksViewModel.taskToShow.assignedToUserID)
            Log.e("XXX- user position ", taskAssigneePosition.toString())
            if(taskAssigneePosition != -1) {
                taskAssignee.setSelection(taskAssigneePosition)
            }

            val taskPriority = view.findViewById<Spinner>(R.id.selectPriority)
            val taskPriorityPosition = tasksViewModel.taskToShow.priority
            Log.e("XXX- task priority ", taskPriorityPosition.toString())
            if(taskPriorityPosition != -1) {
                taskPriority.setSelection(taskPriorityPosition)
            }

            val taskDeadLine = view.findViewById<TextView>(R.id.deadLinePicker)
            val taskDeadLineText = utils.convertLongToTime(tasksViewModel.taskToShow.deadline)
            Log.e("XXX- task deadline ", taskPriorityPosition.toString())
            if(taskDeadLineText.isNotEmpty()) {
                taskDeadLine.setText(taskDeadLineText)
            }

            val taskStatus = view.findViewById<Spinner>(R.id.selectStatus)
            val taskStatusPosition = tasksViewModel.taskToShow.status
            Log.e("XXX- task status ", taskPriorityPosition.toString())
            if(taskStatusPosition != -1) {
                taskStatus.setSelection(taskStatusPosition)
            }

            val taskDepartment = view.findViewById<Spinner>(R.id.selectDepartment)
            val taskDepartmentPosition = getDepartmentViewModel.getDepartmentPositionFromList(tasksViewModel.taskToShow.departmentID)
            Log.e("XXX- task department ", taskDepartmentPosition.toString())
            if(taskDepartmentPosition != -1) {
                taskDepartment.setSelection(taskDepartmentPosition)
            }

            val taskDescription = view.findViewById<EditText>(R.id.taskDescription)
            val taskDescriptionText = tasksViewModel.taskToShow.description
            if(taskDescriptionText.isNotEmpty()) {
                taskDescription.setText(taskDescriptionText)
            }

            view.findViewById<Button>(R.id.createButton).setOnClickListener {

                val title = view.findViewById<TextView>(R.id.taskName).text.toString()
                val description = view.findViewById<TextView>(R.id.taskDescription).text.toString()
                val assigneeToUserIdTemp = view.findViewById<Spinner>(R.id.selectAssignee).selectedItemId.toInt()
                val assigneeToUserId = getUsersViewModel.getUserIdFromList(assigneeToUserIdTemp)
                val priority = view.findViewById<Spinner>(R.id.selectPriority).selectedItemId.toInt()
                val deadLine: Long = 1670272963000
                val departmentIdTemp = view.findViewById<Spinner>(R.id.selectDepartment).selectedItemId.toInt()
                val departmentId = getDepartmentViewModel.getDepartmentIdFromList(departmentIdTemp)
                val status = view.findViewById<Spinner>(R.id.selectStatus).selectedItemId.toInt()

                Log.e("XXX- Task Name ", title)
                Log.e("XXX- Assignee ", "$assigneeToUserId")
                Log.e("XXX- Department ", departmentId.toString())
                Log.e("XXX- DeadLine ", view.findViewById<TextView>(R.id.deadLinePicker).text.toString())
                Log.e("XXX- DeadLinePassed ", deadLine.toString())
                Log.e("XXX- Priority ", priority.toString())
                Log.e("XXX- Status ", status.toString())
                Log.e("XXX- Details ", description)


                updateTaskViewModel.updateTask(tasksViewModel.taskToShow.id, title, description, assigneeToUserId, priority, deadLine, departmentId, status)

                updateTaskViewModel.isSuccessful.observe(this.viewLifecycleOwner) {
                    Log.d(UpdateTaskFragment.TAG, "Update task successfully = $it")
                    if (it) {
                        tasksViewModel.getTasks()
                        Log.e("XXX- Task Update ", updateTaskViewModel.isSuccessful.value.toString())
                        val toast = Toast.makeText(getActivity(), "Task Updated", Toast.LENGTH_SHORT)
                        toast.show();
                        this.findNavController().navigate(R.id.tasksFragment)
                    }else{
                        val toast = Toast.makeText(getActivity(), "Could not update task", Toast.LENGTH_SHORT)
                        toast.show();
                    }
                }


            }



        }


    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        Log.e("XXX- Calendar: ", "$year-$month-$dayOfMonth")
        calendar.set(year, month, dayOfMonth)
        Log.e("XXX- Calendar: ", calendar.timeInMillis.toString())
        val timestamp: Long = (calendar.timeInMillis)
        view?.findViewById<Button>(R.id.deadLinePicker)?.text = formatter.format(timestamp).toString()
    }

    private fun displayFormatedDate( timestamp: Long){
        binding.deadLinePicker.text = formatter.format(timestamp)
    }


}

