package pl.info.czerwinski.android.calculator.processor

data class Value(val text: String) {

	constructor(int: Int) : this(int.toString())
	constructor(long: Long) : this(long.toString())
	constructor(float: Float) : this(if (float.isInt()) float.toInt().toString() else float.toString())
	constructor(double: Double) : this(if (double.isLong()) double.toLong().toString() else double.toString())

	operator fun plus(digit: Char) = Value(
			if (digit == '.')
				if ('.' in text) text else text + digit
			else
				if (text == "0") digit.toString() else text + digit
	)

	operator fun unaryMinus() : Value = Value(if (text.startsWith('-')) text.substring(1) else "-" + text)

	operator fun plus(value: Value) = Value(toDouble() + value.toDouble())
	operator fun minus(value: Value) = Value(toDouble() - value.toDouble())
	operator fun times(value: Value) = Value(toDouble() * value.toDouble())
	operator fun div(value: Value) = Value(toDouble() / value.toDouble())

	fun toDouble() = text.toDouble()

	override fun toString() = text
}
