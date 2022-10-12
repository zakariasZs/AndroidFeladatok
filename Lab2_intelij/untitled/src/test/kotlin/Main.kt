fun String.monogram() : String{
    return this.split(" ").map{it[0]}.joinToString("")
}
fun List<String>.osszefuzes( sep : String) : String{
    return this.joinToString(sep)
}
fun List<String>.longestFromList() : String{
    return this.maxByOrNull { it.length }.toString()
}


fun main(){
//    val dict: IDictionary = ListDictionary
//    println("Number of words: ${dict.size()}")
//    var word: String?
//    while(true){
//        print("What to find? ")
//        word = readLine()
//        if( word.equals("quit")){
//            break
//        }
//        println("Result: ${word?.let { dict.find(it) }}")
//    }

//    //2.feladat
//    val nev = "John Smith"
//    println(nev.monogram())
//
//    var lista = listOf("apple", "pear", "melon")
//    var sep = "#"

//    println(lista.osszefuzes(sep))

//    println(lista.longestFromList())


    //3.feladat

    var cnt = 0
    var correctDateList = mutableListOf<Date>()
    while(cnt<10){
        val current_year = Date.currentDate.current.year
        val temp = Date((0..current_year).random(), (0..13).random(), (0..32).random())

        if(temp.validateDate()) {
            cnt+=1
            correctDateList.add(temp)
        }else{
            println("Hibas datum: ${temp}")
        }
    }
    println("Helyes datumok:")
    correctDateList.forEach(){
        println(it)
    }
    println("Helyes datumok rendezve:")
    correctDateList.sorted().forEach(){
        println(it)
    }

}
