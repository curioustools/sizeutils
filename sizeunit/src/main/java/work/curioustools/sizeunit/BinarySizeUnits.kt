package work.curioustools.sizeunit

import work.curioustools.sizeunit.BinarySizeConstants.BITS_IN_ONE_BYTE
import work.curioustools.sizeunit.BinarySizeConstants.BITS_IN_ONE_GB
import work.curioustools.sizeunit.BinarySizeConstants.BITS_IN_ONE_KB
import work.curioustools.sizeunit.BinarySizeConstants.BITS_IN_ONE_MB
import work.curioustools.sizeunit.BinarySizeConstants.BITS_IN_ONE_PB
import work.curioustools.sizeunit.BinarySizeConstants.BITS_IN_ONE_TB

interface BinarySizeUnits{
    fun toBits():Number
    fun toBitsAsDouble() = toBits().toDouble()
    fun toBytes():Number = (toBitsAsDouble()/ BITS_IN_ONE_BYTE)
    fun toKiloBytes():Number = toBitsAsDouble() / BITS_IN_ONE_KB
    fun toMegaBytes():Number = toBitsAsDouble() / BITS_IN_ONE_MB
    fun toGigaBytes():Number = toBitsAsDouble() / BITS_IN_ONE_GB
    fun toTeraBytes():Number = toBitsAsDouble() / BITS_IN_ONE_TB
    fun toPetaBytes():Number = toBitsAsDouble() / BITS_IN_ONE_PB


    data class Bits(val origVal:Number): BinarySizeUnits {
        override fun toBits(): Number = origVal.toDouble() * BinarySizeConstants.BITS_IN_ONE_BIT
    }

    data class Bytes(val origVal:Number): BinarySizeUnits {
        override fun toBits(): Number = origVal.toDouble() * BITS_IN_ONE_BYTE
    }

    data class KB(val origVal:Number): BinarySizeUnits {
        override fun toBits(): Number = origVal.toDouble() * BITS_IN_ONE_KB
    }

    data class MB(val origVal:Number): BinarySizeUnits {
        override fun toBits(): Number = origVal.toDouble() * BITS_IN_ONE_MB
    }

    data class GB(val origVal:Number): BinarySizeUnits {
        override fun toBits(): Number = origVal.toDouble() * BITS_IN_ONE_GB
    }

    data class TB(val value:Number): BinarySizeUnits {
        override fun toBits(): Number = value.toDouble() * BITS_IN_ONE_TB
    }

    data class PB(val value:Number): BinarySizeUnits {
        override fun toBits(): Number = value.toDouble() * BITS_IN_ONE_TB
    }



}