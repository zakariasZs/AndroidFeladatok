import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.Comparator

data class Date (
    var year: Int = currentDate.current.year,
    var month: Int = currentDate.current.month.value,
    var day: Int= currentDate.current.dayOfMonth,
): Comparable<Date>, Comparator<Date>{

    override operator fun compareTo(other: Date): Int {
        if (this.year > other.year) return 1
        if (this.year < other.year) return -1
        if (this.month > other.month) return 1
        if (this.month < other.month) return -1
        if (this.day > other.day) return 1
        if (this.day < other.day) return -1
        return 0
    }

    fun Int.leapYear() : Boolean{
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                // year is divisible by 400, hence the year is a leap year
                return year % 400 == 0
            } else {
                return true
            }
        } else {
            return false
        }
    }

    fun validateDate() :Boolean {
        return this.year>1960&&this.year<2022&&this.month>0&&this.month<13&&this.day>0&&this.day<=31
    }

    companion object currentDate {
        val calendar = Calendar.getInstance()

        val current = LocalDateTime.of(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            calendar.get(Calendar.SECOND)
        )
    }

    override fun compare(o1: Date?, o2: Date?): Int {
        if(o1==null || o2==null){
            return 0
        }
        return o1.day.compareTo(o2.day)
    }
}