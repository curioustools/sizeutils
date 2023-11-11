package work.curioustools.sizeunit


fun Number.roundOff(precision: Int = 2): Double {
    return String.format("%.${precision}f", toDouble()).toDouble()
}

fun Number.roundOffToNumber(precisionAfterZeros: Int = 3): Double {
    //todo : 2.000001230000  >> 2.00000123
    //2.012345 >> 2.0123
    //2.12345 >> 2.123
    return toDouble()
}
