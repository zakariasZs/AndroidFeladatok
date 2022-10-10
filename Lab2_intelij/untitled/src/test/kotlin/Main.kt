fun String.monogram() : String{
    return this.split(" ").map{it[0]}.joinToString()
}
fun List<String>.osszefuzes( sep : String) : String{
    return this.joinToString(sep)
}


fun main(){
    val dict: IDictionary = ListDictionary
    println("Number of words: ${dict.size()}")
    var word: String?
    while(true){
        print("What to find? ")
        word = readLine()
        if( word.equals("quit")){
            break
        }
        println("Result: ${word?.let { dict.find(it) }}")
    }

    //2.feladat
    val nev:String = "John Smith"
    println(nev.monogram())

    var lista = listOf("apple", "pear", "melon")
    var sep = "#"
    println(lista.osszefuzes(sep))
}
