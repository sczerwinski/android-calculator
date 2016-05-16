package pl.info.czerwinski.android.calculator.processor

object Processor {

	val operations: MutableList<Operation> = mutableListOf()

	fun clear() {
		operations.clear()
	}

	override fun toString(): String {
		val builder = StringBuilder()
		var value: Value = Value("")
		for (operation in operations) {
			when (operation) {
				is UnaryOperation -> value = operation(value)
				is BinaryOperation -> {
					builder.append(value).append(operation.symbol)
					value = Value("")
				}
			}
		}
		builder.append(value)
		return builder.toString();
	}
}
