package com.example.quizapp.viewModel

import androidx.lifecycle.ViewModel

class QuestionListModel: ViewModel() {
    val nrOfQuestions=8
    val questionList = mutableListOf<String>("1. Kotlin is developed by?", "Google", "JetBrains", "Microsoft", "Adobe", "2", "2. Which of following is used to handle null exceptions in Kotlin?", "Range", "Sealed Class", "Elvis Operator", "Lambda function", "3", "3. Which file extension is used to save Kotlin files.", ".java", ".kot", ".kt or .kts", ".andriod", "3", "4. All classes in Kotlin classes are by default?", "public", "final", "sealed", "abstract", "2", "5. What is an immutable variable?", "A variable that cannot change, read-only", "A variable that can be changed", "A variable used for string interpolation", "All of the above", "1", "6. How to make a multi lined comment in Kotlin?", "//", "/* */", "/ multi line comment /", "<??>", "3", "7. How do you get the length of a string in Kotlin?", "str.length", "length(str)", "str.lengthOf", "len(str)", "1", "8. Which of the followings constructors are available in Kotlin?", "Primary constructor", "Secondary constructor", "Both 1 & 2", "None of the above", "3")

}