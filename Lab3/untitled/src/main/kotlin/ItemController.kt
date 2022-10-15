class ItemController (private val itemService : ItemService){
    fun quiz(n: Int){
        val items : List<Item> = itemService.selectRandomItems(n)
        for((i : Int, item : Item) in items.withIndex()){
            println("${i+1}. " + item.question)
            for (j in item.answers.indices){
                print("   ${j + 1}." + item.answers[j])
            }
            println()
            print("Your answer: ")
            val answer : String? = readLine()

            if (answer?.toInt() == item.correct){
                println("Correct answer!")
            } else{
                println("Wrong answer!")
            }
        }
    }
}