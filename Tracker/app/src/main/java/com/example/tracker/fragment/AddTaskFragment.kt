package com.example.tracker.fragment

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
import com.example.tracker.databinding.AddTaskScreenBinding
import com.example.tracker.viewmodel.*
import java.text.SimpleDateFormat
import java.util.*
import com.example.tracker.util.TaskUtil as utilsTask

class AddTaskFragment : Fragment(R.layout.add_task_screen), DatePickerDialog.OnDateSetListener {


    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var binding: AddTaskScreenBinding
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

    private lateinit var taskPostViewModel: TaskPostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factoryUsers = GetUsersViewModelFactory(ThreeTrackerRepository())
        getUsersViewModel = ViewModelProvider(this, factoryUsers)[GetUsersViewModel::class.java]

        val factoryDepartments = GetDepartmentViewModelFactory(ThreeTrackerRepository())
        getDepartmentViewModel = ViewModelProvider(this, factoryDepartments)[GetDepartmentViewModel::class.java]


        val taskPostViewModelFactory = TaskPostViewModelFactory(ThreeTrackerRepository())
        taskPostViewModel = ViewModelProvider(this, taskPostViewModelFactory)[TaskPostViewModel::class.java]
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
        setupUserSpinnerView()
        getUsersViewModel.users.observe(viewLifecycleOwner) {
            usersSpinnerAdapter.setData(getUsersViewModel.users.value as ArrayList<GetUserResponse>)
            usersSpinnerAdapter.notifyDataSetChanged()
            Log.d(AddTaskFragment.TAG, "Tasks list = $it")
        }

        departmentSpiner = view.findViewById(R.id.selectDepartment)
        setupDepartmentSpinnerView()
        getDepartmentViewModel.departments.observe(viewLifecycleOwner) {
            departmentSpinnerAdapter.setData(getDepartmentViewModel.departments.value as ArrayList<GetDepartmentResponse>)
            departmentSpinnerAdapter.notifyDataSetChanged()
            Log.d(AddTaskFragment.TAG, "Departments list = $it")
        }

        prioritytSpiner = view.findViewById(R.id.selectPriority)
        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapterPriority = ArrayAdapter.createFromResource(view.context,
            R.array.priority_array, android.R.layout.simple_spinner_item)
        // Specify the layout to use when the list of choices appears
        adapterPriority.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        prioritytSpiner.adapter = adapterPriority

        statustSpiner = view.findViewById(R.id.selectStatus)
        val adapterStatus = ArrayAdapter.createFromResource(view.context,R.array.status_array, android.R.layout.simple_spinner_item)
        adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        statustSpiner.adapter = adapterStatus

        binding = AddTaskScreenBinding.inflate(inflater)

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

        getUsersViewModel.getUsers()
        getDepartmentViewModel.getDepartments()


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
                .setMessage("Do you really want to cancel the task creation?")
                .setPositiveButton("YES",
                    DialogInterface.OnClickListener { dialog, which -> this.findNavController().navigate(R.id.tasksFragment);})
                .setNegativeButton("NO",
                    DialogInterface.OnClickListener { dialog, which -> }).show()
        }


        view.findViewById<Button>(R.id.createButton).setOnClickListener {

            val title = view.findViewById<TextView>(R.id.taskName).text.toString()
            val description = view.findViewById<TextView>(R.id.taskDescription).text.toString()
            val assigneeToUserIdTemp = view.findViewById<Spinner>(R.id.selectAssignee).selectedItemId.toInt()
            val assigneeToUserId = getUsersViewModel.getUserIdFromList(assigneeToUserIdTemp)
            val priority = view.findViewById<Spinner>(R.id.selectPriority).selectedItemId.toInt()
//            val deadLine = view.findViewById<TextView>(R.id.deadLinePicker).text.toString()
            val deadLine: Long = 1670272963000
            val departmentIdTemp = view.findViewById<Spinner>(R.id.selectDepartment).selectedItemId.toInt()
            val departmentId = getDepartmentViewModel.getDepartmentIdFromList(departmentIdTemp)
            val status = view.findViewById<Spinner>(R.id.selectStatus).selectedItemId.toInt()


//            Log.e("XXX- Task Name ", title)
//            Log.e("XXX- Assignee ", "$assigneeToUserId")
//            Log.e("XXX- Department ", departmentId.toString())
//            Log.e("XXX- DeadLine: ", view.findViewById<TextView>(R.id.deadLinePicker).text.toString())
//            Log.e("XXX- DeadLinePassed: ", deadLine.toString())
//            Log.e("XXX- Priority: ", priority.toString())
//            Log.e("XXX- Status: ", status.toString())
//            Log.e("XXX- Details: ", description)




            taskPostViewModel.taskPost(title, description, assigneeToUserId, priority, deadLine, departmentId, status)

            taskPostViewModel.isSuccessful.observe(this.viewLifecycleOwner) {
                Log.d(TAG, "Task post successfully = $it")
                if (it) {
                    val toast = Toast.makeText(getActivity(), "Task $title Created", Toast.LENGTH_SHORT)
                    toast.show();
                    Log.e("XXX- Task Post ", taskPostViewModel.isSuccessful.toString())
                    findNavController().navigate(R.id.tasksFragment)
                }else{
                    val toast = Toast.makeText(getActivity(), "Error with the data", Toast.LENGTH_SHORT)
                    toast.show();
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