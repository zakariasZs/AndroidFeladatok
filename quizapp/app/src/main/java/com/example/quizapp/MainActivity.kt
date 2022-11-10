package com.example.quizapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.fragment.QuestionListFragment
import com.example.quizapp.fragment.QuizStartFragment
import com.example.quizapp.fragment.UserSettingFragment


class MainActivity : AppCompatActivity() {

    private val TAG: String = javaClass.simpleName

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val usersettingFragment = UserSettingFragment()
    private val quizStartFragment = QuizStartFragment()
    private val questionListFragment = QuestionListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(quizStartFragment)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(quizStartFragment)
                R.id.question -> replaceFragment(questionListFragment)
                R.id.settings -> replaceFragment(usersettingFragment)
            else ->{}
            }
            true
        }



//        startButton.setOnClickListener{
//            Log.d(TAG, "Button clicked")
//            val textField: EditText = findViewById(R.id.editTextTextPersonName)
//            val textFieldText = textField.text
//            Log.d(TAG, textFieldText.toString())
//
//            val toast = Toast.makeText(applicationContext, textFieldText.toString(), Toast.LENGTH_SHORT)
//            toast.show()
//        }

//        val chooseButton : Button = findViewById(R.id.button2)
//        chooseButton.setOnClickListener{
//            Snackbar.make(chooseButton, "Text label", Snackbar.LENGTH_LONG)
//                .setAction("Action") {
//                    val toast = Toast.makeText(applicationContext, "Action pressed", Toast.LENGTH_SHORT)
//                    toast.show()
//                }
//                .show()
//        }


    }

    private fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_view, fragment)
        transaction.commit()
    }


    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart() function called")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop() function called")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause() function called")
    }
}