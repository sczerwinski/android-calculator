package pl.info.czerwinski.android.calculator.processor.operations

import pl.info.czerwinski.android.calculator.processor.Value

class UnaryOperation(val operation: (Value) -> Value) : QueuedOperation() {
	operator fun invoke(value: Value) = operation(value)
}
