package pl.info.czerwinski.android.calculator.processor.operations

import pl.info.czerwinski.android.calculator.processor.Value

class BinaryOperation(val symbol: String, val operation: (Value, Value) -> Value) : QueuedOperation() {

	operator fun invoke(v1: Value, v2: Value) = operation(v1, v2)
}
