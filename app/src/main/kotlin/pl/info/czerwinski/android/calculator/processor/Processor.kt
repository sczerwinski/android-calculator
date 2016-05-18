package pl.info.czerwinski.android.calculator.processor

import pl.info.czerwinski.android.calculator.processor.operations.BinaryOperation
import pl.info.czerwinski.android.calculator.processor.operations.Operation
import pl.info.czerwinski.android.calculator.processor.operations.UnaryOperation

object Processor {

	val operations: MutableList<Operation> = mutableListOf()

	fun clear() {
		operations.clear()
	}

	fun back() {
		if (operations.isNotEmpty()) {
			operations.removeAt(operations.lastIndex)
		}
	}

	fun calculate() {
		val values = mutableListOf<Value>()
		val binaryOperations = mutableListOf<BinaryOperation>()

		var value = Value("")
		for (operation in operations) {
			when (operation) {
				is UnaryOperation -> value = operation(value)
				is BinaryOperation -> {
					values.add(value)
					value = Value("")
					binaryOperations.add(operation)
				}
			}
		}
		values.add(value)

		binaryOperations.add(BinaryOperation("=") { x, y -> x })

		clear()
		operations.add(values
				.zip(binaryOperations)
				.reduce { total, next -> Pair(total.second(total.first, next.first), next.second) }
				.first
				.toOperation())
	}

	override fun toString(): String {
		val builder = StringBuilder()
		var value = Value("")
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
		return builder.toString()
	}
}
