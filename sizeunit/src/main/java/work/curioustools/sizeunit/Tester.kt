package work.curioustools.sizeunit

import work.curioustools.sizeunit.SizeUnit.Companion.DECIMAL_BASE
import work.curioustools.sizeunit.SizeUnit.Companion.BINARY_BASE
import work.curioustools.sizeunit.helpers.bits
import work.curioustools.sizeunit.helpers.bytes
import work.curioustools.sizeunit.helpers.gb
import work.curioustools.sizeunit.helpers.kb
import work.curioustools.sizeunit.helpers.mb
import work.curioustools.sizeunit.helpers.pb
import work.curioustools.sizeunit.helpers.roundOff
import work.curioustools.sizeunit.helpers.tb
import work.curioustools.sizeunit.helpers.toMemoryString

@Deprecated("This is a test class only, not intended for prod use")
class Tester {

    companion object{

        @JvmStatic
        fun testString():String{
            return buildString {
                appendLine("1. Basic Conversions ============")
                arrayOf(
                    SizeUnit.Bits(100_00_00),
                    SizeUnit.Bits(4096_000),
                    SizeUnit.Bytes(1),
                    SizeUnit.KB(1),
                    SizeUnit.MB(1),
                    SizeUnit.MB(4),
                    SizeUnit.GB(1),
                    SizeUnit.TB(1),
                    SizeUnit.PB(1),
                ).forEach {
                    val orig = "${it.originalValue} ${it::class.simpleName}"
                    appendLine("$orig = ${it.toBits(DECIMAL_BASE)} b | ${it.toBits(BINARY_BASE)} b")
                    appendLine("$orig = ${it.toBytes(DECIMAL_BASE)} B | ${it.toBytes(BINARY_BASE)} B")
                    appendLine("$orig = ${it.toKiloBytes(DECIMAL_BASE)} KB | ${it.toKiloBytes(BINARY_BASE)} KiB")
                    appendLine("$orig = ${it.toMegaBytes(DECIMAL_BASE)} MB | ${it.toMegaBytes(BINARY_BASE)} MiB")
                    appendLine("$orig = ${it.toGigaBytes(DECIMAL_BASE)} GB | ${it.toGigaBytes(BINARY_BASE)} GiB")
                    appendLine("$orig = ${it.toTeraBytes(DECIMAL_BASE)} TB | ${it.toTeraBytes(BINARY_BASE)} TiB")
                    appendLine("$orig = ${it.toPetaBytes(DECIMAL_BASE)} PB | ${it.toPetaBytes(BINARY_BASE)} PiB")
                    appendLine("--------")
                }

                appendLine("2. Extension: Inline Notation ============")
                arrayOf(100_00_00.bits,1.55.bytes,1.55.kb,1.55.mb,1.55.gb,1.55.tb,10.55.pb).forEach {
                    val orig = "${it.originalValue} ${it::class.simpleName}"
                    appendLine("$orig = ${it.toBits(DECIMAL_BASE)} b | ${it.toBits(BINARY_BASE)} b")
                    appendLine("$orig = ${it.toBytes(DECIMAL_BASE)} B | ${it.toBytes(BINARY_BASE)} B")
                    appendLine("$orig = ${it.toKiloBytes(DECIMAL_BASE)} KB | ${it.toKiloBytes(BINARY_BASE)} KiB")
                    appendLine("$orig = ${it.toMegaBytes(DECIMAL_BASE)} MB | ${it.toMegaBytes(BINARY_BASE)} MiB")
                    appendLine("$orig = ${it.toGigaBytes(DECIMAL_BASE)} GB | ${it.toGigaBytes(BINARY_BASE)} GiB")
                    appendLine("$orig = ${it.toTeraBytes(DECIMAL_BASE)} TB | ${it.toTeraBytes(BINARY_BASE)} TiB")
                    appendLine("$orig = ${it.toPetaBytes(DECIMAL_BASE)} PB | ${it.toPetaBytes(BINARY_BASE)} PiB")
                    appendLine("------")
                }

                appendLine("3. Long,Double and Round off notation ===============")
                SizeUnit.Bits(999_999_9).let {
                    val orig = "${it.originalValue} ${it::class.simpleName}"
                    appendLine("$orig: ${it.toBits(BINARY_BASE).toLong()} b | ${it.toBits(BINARY_BASE)} b |  ${it.toBits(BINARY_BASE).toLong().roundOff()} b ")
                    appendLine("$orig: ${it.toBytes().toLong()} B | ${it.toBytes()} B | ${it.toBytes().toLong().roundOff(2)} B")
                    appendLine("$orig: ${it.toKiloBytes().toLong()} KB | ${it.toKiloBytes()} KB | ${it.toKiloBytes().roundOff(2)} KB")
                    appendLine("$orig: ${it.toMegaBytes().toLong()} MB | ${it.toMegaBytes()} MB | ${it.toMegaBytes().roundOff(2)} MB")
                    appendLine("$orig: ${it.toGigaBytes().toLong()} GB | ${it.toGigaBytes()} GB | ${it.toGigaBytes().roundOff(2)} GB")
                    appendLine("$orig: ${it.toTeraBytes().toLong()} TB | ${it.toTeraBytes()} TB | ${it.toTeraBytes().roundOff(2)} TB")
                    appendLine("$orig: ${it.toPetaBytes().toLong()} PB | ${it.toPetaBytes()} PB | ${it.toPetaBytes().roundOff(2)} PB")
                }


                appendLine("4. Extension: toMemoryString(base:1024,1000)==============")
                var x = 9223372036854775807 //largest long value
                appendLine("largest long value is  : 9223372036854775807")
                while (x > 1) {
                    val memoryStringBinary = x.toMemoryString(BINARY_BASE)
                    val memoryStringDecimal =x.toMemoryString(DECIMAL_BASE)
                    val sizeUnit = SizeUnit.Bytes(x)
                    val sizeUnitBinary = when{
                        memoryStringBinary.contains("P") -> "${sizeUnit.toPetaBytes(BINARY_BASE).roundOff()} PiB"
                        memoryStringBinary.contains("T") -> "${sizeUnit.toTeraBytes(BINARY_BASE).roundOff()} TiB"
                        memoryStringBinary.contains("G") -> "${sizeUnit.toGigaBytes(BINARY_BASE).roundOff()} GiB"
                        memoryStringBinary.contains("M") -> "${sizeUnit.toMegaBytes(BINARY_BASE).roundOff()} MiB"
                        memoryStringBinary.contains("K") -> "${sizeUnit.toKiloBytes(BINARY_BASE).roundOff()} KiB"
                        else -> "${sizeUnit.toBytes(BINARY_BASE).roundOff()} B"
                    }

                    val sizeUnitDecimal = when{
                        memoryStringDecimal.contains("P") -> "${sizeUnit.toPetaBytes(DECIMAL_BASE).roundOff()} PB"
                        memoryStringDecimal.contains("T") -> "${sizeUnit.toTeraBytes(DECIMAL_BASE).roundOff()} TB"
                        memoryStringDecimal.contains("G") -> "${sizeUnit.toGigaBytes(DECIMAL_BASE).roundOff()} GB"
                        memoryStringDecimal.contains("M") -> "${sizeUnit.toMegaBytes(DECIMAL_BASE).roundOff()} MB"
                        memoryStringDecimal.contains("K") -> "${sizeUnit.toKiloBytes(DECIMAL_BASE).roundOff()} KB"
                        else -> "${sizeUnit.toBytes(DECIMAL_BASE).roundOff()} B"
                    }

                    appendLine("$x =  $memoryStringDecimal(actual:$sizeUnitDecimal) \t\t\t|\t\t\t $memoryStringBinary(actual:$sizeUnitBinary)")
                    x /= 10
                }




            }
        }

        @JvmStatic
        fun main(args: Array<String>) {
            println(testString())
        }
    }
}
