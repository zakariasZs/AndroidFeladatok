interface IDictionary {
    fun add(word: String): Boolean
    fun find(word: String): Boolean
    fun size(): Int


    companion object {
        val DICTIONARY_NAME = "E:\\UNI\\4ev\\1felev\\AndroidProgramozas\\AndroidFeladatok\\Lab2_intelij\\untitled\\src\\test\\resources\\dict.txt"
    }
}