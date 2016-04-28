package pl.info.czerwinski.android.calculator.processor

object Processor {

	val operations: MutableList<Operation> = mutableListOf(UnaryOperation { it + '0' })

	fun clear() {
		operations.clear()
		operations.add(UnaryOperation { it + '0' })
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
