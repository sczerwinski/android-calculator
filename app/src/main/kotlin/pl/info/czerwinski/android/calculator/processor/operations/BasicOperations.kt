package pl.info.czerwinski.android.calculator.processor.operations

import pl.info.czerwinski.android.calculator.processor.Processor
import pl.info.czerwinski.android.calculator.processor.Value

abstract class QueuedOperation : Operation {
	override fun push() {
		Processor.operations.add(this)
	}
}

class UnaryOperation(val operation: (Value) -> Value) : QueuedOperation() {
	operator fun invoke(value: Value) = operation(value)
}


class BinaryOperation(val symbol: String, val precedence: Int, val operation: (Value, Value) -> Value) : QueuedOperation() {
	operator fun invoke(v1: Value, v2: Value) = operation(v1, v2)
}
