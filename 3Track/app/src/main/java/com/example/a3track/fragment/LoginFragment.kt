package com.example.a3track.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.a3track.R
import com.example.a3track.api.TrackRepository
import com.example.a3track.viewmodel.LoginViewModel
import com.example.a3track.viewmodel.LoginViewModelFactory
import com.example.a3track.App
import com.example.a3track.manager.SharedPreferencesManager
//
//class LoginFragment : Fragment() {
//    val TAG: String = javaClass.simpleName
//
//    private lateinit var loginViewModel: LoginViewModel
////    protected open var bottomNavigationViewVisibility = View.GONE
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val factory = LoginViewModelFactory(TrackRepository())
//        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
////        if (activity is MainActivity) {
////            var  mainActivity = activity as MainActivity
////            mainActivity.setBottomNavigationVisibility(bottomNavigationViewVisibility)
////        }
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view = inflater.inflate(R.layout.login_screen, container, false)
//
//        val userNameEditText: EditText = view.findViewById(R.id.edittext_name_login_fragment)
//        val passwordEditText: EditText = view.findViewById(R.id.edittext_password_login_fragment)
//        val button: Button = view.findViewById(R.id.button_login_fragment)
//
//        Log.d(TAG, "token = " + App.sharedPreferences.getStringValue(SharedPreferencesManager.KEY_TOKEN, "Empty token!"))
//
//        button.setOnClickListener {
//            loginViewModel.passwordKey = userNameEditText.text.toString()
//            loginViewModel.email = passwordEditText.text.toString()
//
//            loginViewModel.login()
//
//            loginViewModel.isSuccessful.observe(this.viewLifecycleOwner) {
//                Log.d(TAG, "Logged in successfully = " + it)
//                if (it) {
//                    findNavController().navigate(R.id.activityScreenFragment)
//                }
//            }
//        }
//
//        return view
//    }
//}



class LoginFragment : Fragment() {
    val TAG: String = javaClass.simpleName

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = LoginViewModelFactory(TrackRepository())
        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.login_screen, container, false)

        val userNameEditText: EditText = view.findViewById(R.id.edittext_name_login_fragment)
        val passwordEditText: EditText = view.findViewById(R.id.edittext_password_login_fragment)
        val button: Button = view.findViewById(R.id.button_login_fragment)

        Log.d(TAG, "token = " + App.sharedPreferences.getStringValue(SharedPreferencesManager.KEY_TOKEN, "Empty token!"))

        button.setOnClickListener {
            loginViewModel.email = userNameEditText.text.toString()
            loginViewModel.passwordKey = passwordEditText.text.toString()

            loginViewModel.login()

            loginViewModel.isSuccessful.observe(this.viewLifecycleOwner) {
                Log.d(TAG, "Logged in successfully = " + it)
                if (it) {
                    findNavController().navigate(R.id.activityScreenFragment)
                }
            }
        }

        return view
    }
}