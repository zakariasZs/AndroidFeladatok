import android.content.ContentValues
import android.util.Log
import java.io.File

object ItemRepository {
    private var items = mutableListOf<Item>();
    init {
        val lines = listOf("1. Kotlin is developed by?", "Google", "JetBrains", "Microsoft", "Adobe", "2", "2. Which of following is used to handle null exceptions in Kotlin?", "Range", "Sealed Class", "Elvis Operator", "Lambda function", "3", "3. Which file extension is used to save Kotlin files.", ".java", ".kot", ".kt or .kts", ".andriod", "3", "4. All classes in Kotlin classes are by default?", "public", "final", "sealed", "abstract", "2", "5. What is an immutable variable?", "A variable that cannot change, read-only", "A variable that can be changed", "A variable used for string interpolation", "All of the above", "1", "6. How to make a multi lined comment in Kotlin?", "//", "/* */", "/ multi line comment /", "<??>", "3", "7. How do you get the length of a string in Kotlin?", "str.length", "length(str)", "str.lengthOf", "len(str)", "1", "8. Which of the followings constructors are available in Kotlin?", "Primary constructor", "Secondary constructor", "Both 1 & 2", "None of the above", "3")

        for(i in lines.indices step 6){
            val question = lines[i]
            val ans1 = lines[i+1]
            val ans2 = lines[i+2]
            val ans3 = lines[i+3]
            val ans4 = lines[i+4]
            val correct = lines[i+5]


//            Log.e(ContentValues.TAG, "XXX-$ans1")
//            Log.e(ContentValues.TAG, "XXX-$ans2")
//            Log.e(ContentValues.TAG, "XXX-$ans3")
//            Log.e(ContentValues.TAG, "XXX-$ans4")
//            Log.e(ContentValues.TAG, "XXX-$correct")

            save(Item(question, mutableListOf(ans1,ans2,ans3,ans4), correct.toInt()))

        }
//        Log.e(ContentValues.TAG, "XXX-${items.size}")
//        Log.e(ContentValues.TAG, "XXX-${items[0].question}")

    }
    fun randomItem() = items.random()
    private fun save(item:Item) = items.add(item)
    fun size() = items.size
}