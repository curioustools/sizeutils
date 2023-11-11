package work.curioustools.sizeunit

class Tester {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            println("Binary---")
            BinarySizeUnits.MB(4).let {
                "${it.origVal}:${it::class.simpleName} = ${it.toBits().roundOff()}b".let { s -> println(s) }
                "${it.origVal}:${it::class.simpleName} = ${it.toBytes().roundOff()}B".let { s -> println(s) }
                "${it.origVal}:${it::class.simpleName} = ${it.toKiloBytes().roundOff()}KB".let { s -> println(s) }
                "${it.origVal}:${it::class.simpleName} = ${it.toMegaBytes().roundOff()}MB".let { s -> println(s) }
                "${it.origVal}:${it::class.simpleName} = ${it.toGigaBytes().roundOff()}GB".let { s -> println(s) }
                "${it.origVal}:${it::class.simpleName} = ${it.toTeraBytes().roundOff()}TB".let { s -> println(s) }
                "${it.origVal}:${it::class.simpleName} = ${it.toPetaBytes().roundOff()}PB".let { s -> println(s) }
            }


            println("Decimal----")

            DecimalSizeUnits.MB(4).let {
                "${it.value}:${it::class.simpleName} = ${it.toBits().roundOff()}b".let { s -> println(s) }
                "${it.value}:${it::class.simpleName} = ${it.toBytes().roundOff()}B".let { s -> println(s) }
                "${it.value}:${it::class.simpleName} = ${it.toKiloBytes().roundOff()}KB".let { s -> println(s) }
                "${it.value}:${it::class.simpleName} = ${it.toMegaBytes().roundOff()}MB".let { s -> println(s) }
                "${it.value}:${it::class.simpleName} = ${it.toGigaBytes().roundOff()}GB".let { s -> println(s) }
                "${it.value}:${it::class.simpleName} = ${it.toTeraBytes().roundOff()}TB".let { s -> println(s) }
                "${it.value}:${it::class.simpleName} = ${it.toPetaBytes().roundOff()}PB".let { s -> println(s) }
            }
        }
    }
}
