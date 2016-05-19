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

		var value = Value.EMPTY
		for (operation in operations) {
			when (operation) {
				is UnaryOperation -> value = operation(value)
				is BinaryOperation -> {
					values.add(value)
					value = Value.EMPTY
					binaryOperations.add(operation)
				}
			}
		}
		values.add(value)

		binaryOperations.add(BinaryOperation("=", -1) { x, y -> x })

		clear()
		var calculationChain = values.zip(binaryOperations)
		var precedence = 0
		while (calculationChain.size > 1) {
			calculationChain = calculationChain.drop(1).fold(calculationChain.take(1)) { chain, next ->
				if (chain.last().second.precedence == precedence)
					chain.dropLast(1) + Pair(chain.last().second(chain.last().first, next.first), next.second)
				else
					chain + next
			}
			precedence++
		}
		operations.add(calculationChain.first().first.toOperation())
	}

	override fun toString(): String {
		val builder = StringBuilder()
		var value = Value.EMPTY
		for (operation in operations) {
			when (operation) {
				is UnaryOperation -> value = operation(value)
				is BinaryOperation -> {
					builder.append(value).append(operation.symbol)
					value = Value.EMPTY
				}
			}
		}
		builder.append(value)
		return builder.toString()
	}
}
