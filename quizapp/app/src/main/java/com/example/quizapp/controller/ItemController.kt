import android.content.ContentValues
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.example.quizapp.R
import com.example.quizapp.viewModel.UserViewModel

class ItemController(
    private val itemService: ItemService,
    private val requireActivity: FragmentActivity,
    private val questionText: TextView,
    private val arrayListOf: ArrayList<RadioButton>,
    private val nextButton: Button,
    private val radioGroup: RadioGroup
) {
    private var currentQuestion = 0
    private val model: UserViewModel by requireActivity.viewModels()

    fun quiz(items: List<Item>) {
        radioGroup.clearCheck()
        questionText.text = items[currentQuestion].question
        for (j in items[currentQuestion].answers.indices) {
            arrayListOf[j].text = items[currentQuestion].answers[j]
        }

        handleNext(items)
    }

    private fun handleNext(items: List<Item>) {
        nextButton.setOnClickListener {
            val selectedRadioButton = radioGroup.checkedRadioButtonId
            val radioButton: View? = requireActivity.findViewById(selectedRadioButton)
            val idx: Int =
                requireActivity.findViewById<RadioGroup>(R.id.radioGroup).indexOfChild(radioButton)
            if (selectedRadioButton != -1) {
                if (currentQuestion < model.nrOfQuestions) {
                    if (idx == items[currentQuestion].correct - 1) {
                        model.nrOfCorrectAnswers += 1
                    }
                    currentQuestion += 1
                    if (currentQuestion == model.nrOfQuestions) {
                        requireActivity.findNavController(R.id.fragment_container_view)
                            .navigate(R.id.quizEndFragment)
                    } else {
                        quiz(items)
                    }
                }
            } else {
                currentQuestion += 1
                if (currentQuestion == model.nrOfQuestions) {
                    Log.e("tag", "hali")
                    requireActivity.findNavController(R.id.fragment_container_view)
                        .navigate(R.id.quizEndFragment)
                } else {
                    quiz(items)
                }
            }
        }
    }
}