package work.curioustools.sizeunit

import work.curioustools.sizeunit.SizeUnits.Companion.DECIMAL_BASE
import work.curioustools.sizeunit.SizeUnits.Companion.BINARY_BASE
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
                    SizeUnits.Bits(100_00_00),
                    SizeUnits.Bytes(1),
                    SizeUnits.KB(1),
                    SizeUnits.MB(1),
                    SizeUnits.GB(1),
                    SizeUnits.TB(1),
                    SizeUnits.PB(1),
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
                SizeUnits.GB(102.97699).let {
                    val orig = "${it.originalValue} ${it::class.simpleName}"
                    appendLine("$orig: ${it.toBits(BINARY_BASE).toLong()} b | ${it.toBits(BINARY_BASE)} b |  ${it.toBits(BINARY_BASE).toLong().roundOff()} b ")
                    appendLine("$orig: ${it.toBytes().toLong()} B | ${it.toBytes()} B | ${it.toBytes().toLong().roundOff()} B")
                    appendLine("$orig: ${it.toKiloBytes().toLong()} KB | ${it.toKiloBytes()} KB | ${it.toKiloBytes().roundOff()} KB")
                    appendLine("$orig: ${it.toMegaBytes().toLong()} MB | ${it.toMegaBytes()} MB | ${it.toMegaBytes().roundOff()} MB")
                    appendLine("$orig: ${it.toGigaBytes().toLong()} GB | ${it.toGigaBytes()} GB | ${it.toGigaBytes().roundOff()} GB")
                    appendLine("$orig: ${it.toTeraBytes().toLong()} TB | ${it.toTeraBytes()} TB | ${it.toTeraBytes().roundOff()} TB")
                    appendLine("$orig: ${it.toPetaBytes().toLong()} PB | ${it.toPetaBytes()} PB | ${it.toPetaBytes().roundOff()} PB")
                }


                appendLine("4. Extension: toMemoryString(base:1024,1000)==============")
                var x = 9223372036854775807 //largest long value
                appendLine("largest long value is  : 9223372036854775807")
                while (x > 1) {
                    appendLine("$x = ${x.toMemoryString()} | ${x.toMemoryString(base = DECIMAL_BASE, showI = true)}")
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
