package work.curioustools.sizeunit.helpers

import work.curioustools.sizeunit.SizeUnits
import work.curioustools.sizeunit.SizeUnits.*
import java.lang.Long.signum
import java.text.CharacterIterator
import java.text.StringCharacterIterator
import java.util.Locale
import kotlin.math.abs

inline val Number.bits : Bits get() = Bits(this)
inline val Number.bytes : Bytes get() = Bytes(this)
inline val Number.kb : KB get() = KB(this)
inline val Number.mb : MB get() = MB(this)
inline val Number.gb : GB get() = GB(this)
inline val Number.tb : TB get() = TB(this)
inline val Number.pb : PB get() = PB(this)

/**
 * Helper Extension to round off a number(mostly double) to a particular level of precision.
 * Eg : 9.23567 will show as 9.236
 */
fun Number.roundOff(precision: Int = 3): Double {
    return String.format("%.${precision}f", toDouble()).toDouble()
}

/**
 * Helper Extension to convert a number to a size unit of nearest ceiling. so, if we have a number
 * 922, it will show as 922 B. if we have a number 9223, it will show as 9.2KB.if we have a number
 * 9223372, it will show as 9.0 MB
 */
fun Long.toMemoryString(base:Int = SizeUnits.DECIMAL_BASE, showI:Boolean=false): String {
    val bytes = this
    val absB = if (bytes == Long.MIN_VALUE) Long.MAX_VALUE else abs(bytes)
    if (absB < base) {
        return "$bytes B"
    }
    var value = absB
    val ci: CharacterIterator = StringCharacterIterator("KMGTPE")
    var i = 40
    while (i >= 0 && absB > 0xfffccccccccccccL shr i) {
        value = value.shr(10)
        ci.next()
        i -= 10
    }

    value *= signum(bytes).toLong()
    val letterI=if(showI)"i" else ""
    return String.format(Locale.getDefault(),"%.1f %c${letterI}B", value / base.toDouble(), ci.current())
}

