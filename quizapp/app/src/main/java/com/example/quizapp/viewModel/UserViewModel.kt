package com.example.quizapp.viewModel

import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    var nrOfCorrectAnsers = 0
    var nrOfQuestions = 0
    var userName :String =""
}