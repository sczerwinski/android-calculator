package pl.info.czerwinski.android.calculator.processor

fun Float.isInt () = (this.toDouble() == Math.floor(this.toDouble())) && !isInfinite()

fun Double.isLong () = (this == Math.floor(this)) && !isInfinite()
