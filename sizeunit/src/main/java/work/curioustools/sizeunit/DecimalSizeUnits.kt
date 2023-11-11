package work.curioustools.sizeunit

import work.curioustools.sizeunit.DecimalSizeConstants.BITS_IN_ONE_BYTE
import work.curioustools.sizeunit.DecimalSizeConstants.BITS_IN_ONE_GB
import work.curioustools.sizeunit.DecimalSizeConstants.BITS_IN_ONE_KB
import work.curioustools.sizeunit.DecimalSizeConstants.BITS_IN_ONE_MB
import work.curioustools.sizeunit.DecimalSizeConstants.BITS_IN_ONE_PB
import work.curioustools.sizeunit.DecimalSizeConstants.BITS_IN_ONE_TB

interface DecimalSizeUnits{
    fun toBits():Number
    fun toBitsAsDouble() = toBits().toDouble()
    fun toBytes():Number = (toBitsAsDouble()/ BITS_IN_ONE_BYTE)
    fun toKiloBytes():Number = toBitsAsDouble() / BITS_IN_ONE_KB
    fun toMegaBytes():Number = toBitsAsDouble() / BITS_IN_ONE_MB
    fun toGigaBytes():Number = toBitsAsDouble() / BITS_IN_ONE_GB
    fun toTeraBytes():Number = toBitsAsDouble() / BITS_IN_ONE_TB
    fun toPetaBytes():Number = toBitsAsDouble() / BITS_IN_ONE_PB

    data class Bits(val value:Number): DecimalSizeUnits {
        override fun toBits(): Number = value.toDouble() * BinarySizeConstants.BITS_IN_ONE_BIT
    }

    data class Bytes(val value:Number): DecimalSizeUnits {
        override fun toBits(): Number = value.toDouble() * BITS_IN_ONE_BYTE
    }

    data class KB(val value:Number): DecimalSizeUnits {
        override fun toBits(): Number = value.toDouble() * BITS_IN_ONE_KB
    }

    data class MB(val value:Number): DecimalSizeUnits {
        override fun toBits(): Number = value.toDouble() * BITS_IN_ONE_MB
    }

    data class GB(val value:Number): DecimalSizeUnits {
        override fun toBits(): Number = value.toDouble() * BITS_IN_ONE_GB
    }

    data class TB(val value:Number): DecimalSizeUnits {
        override fun toBits(): Number = value.toDouble() * BITS_IN_ONE_TB
    }

    data class PB(val value:Number): DecimalSizeUnits {
        override fun toBits(): Number = value.toDouble() * BITS_IN_ONE_TB
    }
}