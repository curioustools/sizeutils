package work.curioustools.sizeunit

import work.curioustools.sizeunit.SizeUnit.BitsIn.ONE_BIT
import work.curioustools.sizeunit.SizeUnit.BitsIn.ONE_BYTE

import work.curioustools.sizeunit.SizeUnit.BitsIn.ONE_KB
import work.curioustools.sizeunit.SizeUnit.BitsIn.ONE_KiB

import work.curioustools.sizeunit.SizeUnit.BitsIn.ONE_MB
import work.curioustools.sizeunit.SizeUnit.BitsIn.ONE_MiB

import work.curioustools.sizeunit.SizeUnit.BitsIn.ONE_GB
import work.curioustools.sizeunit.SizeUnit.BitsIn.ONE_GiB

import work.curioustools.sizeunit.SizeUnit.BitsIn.ONE_TB
import work.curioustools.sizeunit.SizeUnit.BitsIn.ONE_TiB

import work.curioustools.sizeunit.SizeUnit.BitsIn.ONE_PB
import work.curioustools.sizeunit.SizeUnit.BitsIn.ONE_PiB

/**
 *
 * # ARCHITECTURE
 *
 * Some Important decisions taken during architecting this class:
 *
 * 1. Input/Output : Input params are from [Number] class and output params are of [Double]
 *    This was done to make input/output seamless for all different kind of java formats : Int,
 *    Float, Double, Long, etc
 *
 * 2. Abstract Class and default implementations : There are multiple reasonings for this architecture:
 *     1. [TimeUnit] converts data like this `TimeUnit.MINUTES.toDays(5L)` . It uses enum for
 *        `MINUTE`, `SECOND` , `DAY` , etc and have specific implementation at multiple areas.
 *        I also wanted to do the same , but instead of user providing the value for original
 *        unit at the time of conversion, i wanted the user to provide the value of original unit
 *        at the time when they are deciding the unit itself.
 *     2. Some functions are implemented in abstract class, while others are implemented in the
 *        children class because of simple maths and how it helps to reduce duplication. For eg,
 *        if we need to convert 4MB to KB , we just need to know how ,many bits are in 4MB, then we
 *        can divide those bits by bits in 1KB and get the value. thus , 4mb = 4x1000x1000x8 bits
 *        and 1kb = 1000*8bits , so 4mb  = (4x1000x1000x8)/(1000x8) kb = 4000 kb. This will be same
 *        for multiple kinds of conversions, so it is helpful not duplicate in same class.
 *    3. We could have used Sealed class instead of abstract class, but that looked like an overkill
 *       as most apps won't be needing to use a `when(){...}` block for iterating over [SizeUnits]'s
 *       child classes. its a unit and not a response status.
 *    4. We used base class param instead of just the child class to have the param/runtime
 *       param because the user might need to access the original value again.
 * 3. Binary and Decimal Base : Many memory systems use  SI systems for representing memory .
 *    where 1kb = 1000 bytes. however other systems use IC systems, where  1 kb = 1024 bytes and so forth.
 *    so we provided the ability to choose the base for conversion in the hands of the user. the
 *    default is 1000
 *
 * > Note:  There is a small limitation to the current architecture that user needs to provide a base
 *   for converting to bits. This limitation does not exist for other conversions as we have
 *   provided a default value in  function itself
 *
 */
abstract class SizeUnit(open val originalValue: Number) {

    object BitsIn {
        const val ONE_BIT: Long = 1
        const val ONE_BYTE: Long = 8

        const val ONE_KB: Long = 1000L * 8
        const val ONE_MB: Long = 1000L * 1000L * 8
        const val ONE_GB: Long = 1000L * 1000L * 1000L * 8
        const val ONE_TB: Long = 1000L * 1000L * 1000L * 1000L * 8
        const val ONE_PB: Long = 1000L * 1000L * 1000L * 1000L * 1000L * 8

        const val ONE_KiB: Long = 1024L * 8
        const val ONE_MiB: Long = 1024L * 1024L * 8
        const val ONE_GiB: Long = 1024L * 1024L * 1024L * 8
        const val ONE_TiB: Long = 1024L * 1024L * 1024L * 1024L * 8
        const val ONE_PiB: Long = 1024L * 1024L * 1024L * 1024L * 1024L * 8
    }

    companion object {
        const val BINARY_BASE = 1024
        const val DECIMAL_BASE = 1000
    }


    abstract fun toBits(base: Int): Number
    fun toBytes(base: Int = DECIMAL_BASE) = (toBits(base).toDouble() / ONE_BYTE)
    fun toKiloBytes(base: Int = DECIMAL_BASE) = (toBits(base).toDouble()) / (if (base == DECIMAL_BASE) ONE_KB else ONE_KiB)
    fun toMegaBytes(base: Int = DECIMAL_BASE) = (toBits(base).toDouble()) / (if (base == DECIMAL_BASE) ONE_MB else ONE_MiB)
    fun toGigaBytes(base: Int = DECIMAL_BASE) = (toBits(base).toDouble()) / (if (base == DECIMAL_BASE) ONE_GB else ONE_GiB)
    fun toTeraBytes(base: Int = DECIMAL_BASE) = (toBits(base).toDouble()) / (if (base == DECIMAL_BASE) ONE_TB else ONE_TiB)
    fun toPetaBytes(base: Int = DECIMAL_BASE) = (toBits(base).toDouble()) / (if (base == DECIMAL_BASE) ONE_PB else ONE_PiB)


    data class Bits(override val originalValue: Number) : SizeUnit(originalValue) {
        override fun toBits(base: Int) = originalValue.toDouble() * ONE_BIT
    }

    data class Bytes(override val originalValue: Number) : SizeUnit(originalValue) {
        override fun toBits(base: Int) = originalValue.toDouble() * ONE_BYTE
    }

    data class KB(override val originalValue: Number) : SizeUnit(originalValue) {
        override fun toBits(base: Int) = originalValue.toDouble() * (if (base == DECIMAL_BASE) ONE_KB else ONE_KiB)
    }

    data class MB(override val originalValue: Number) : SizeUnit(originalValue) {
        override fun toBits(base: Int) = originalValue.toDouble() * (if (base == DECIMAL_BASE) ONE_MB else ONE_MiB)
    }

    data class GB(override val originalValue: Number) : SizeUnit(originalValue) {
        override fun toBits(base: Int) = originalValue.toDouble() * (if (base == DECIMAL_BASE) ONE_GB else ONE_GiB)
    }

    data class TB(override val originalValue: Number) : SizeUnit(originalValue) {
        override fun toBits(base: Int) = originalValue.toDouble() * (if (base == DECIMAL_BASE) ONE_TB else ONE_TiB)
    }

    data class PB(override val originalValue: Number) : SizeUnit(originalValue) {
        override fun toBits(base: Int) = originalValue.toDouble() * (if (base == DECIMAL_BASE) ONE_PB else ONE_PiB)
    }



}