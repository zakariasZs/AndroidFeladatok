import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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
        questionText.text = items[currentQuestion].question
        for (j in items[currentQuestion].answers.indices) {
            arrayListOf[currentQuestion].text = items[0].answers[j]
        }
        handleNext(items)
    }

    private fun handleNext(items: List<Item>) {
        nextButton.setOnClickListener {
            val selectedRadioButton = radioGroup.checkedRadioButtonId
            if (selectedRadioButton != -1) {
                if (currentQuestion < items.size) {
                    if (selectedRadioButton == items[currentQuestion].correct - 1) {
                        model.nrOfCorrectAnswers += 1
                    }
                    currentQuestion += 1
                    quiz(items)
                } else {
                    requireActivity.findNavController(R.id.navigation)
                        .navigate(R.id.quizEndFragment)
                }
            } else {
                currentQuestion += 1
                quiz(items)
            }
        }
    }
}