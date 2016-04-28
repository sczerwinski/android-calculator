package pl.info.czerwinski.android.calculator.processor

class UnaryOperation(val operation: (Value) -> Value) : QueuedOperation() {
	operator fun invoke(value: Value) = operation(value)
}
