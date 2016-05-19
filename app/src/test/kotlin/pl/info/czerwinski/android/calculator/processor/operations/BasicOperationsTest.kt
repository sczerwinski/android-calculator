package pl.info.czerwinski.android.calculator.processor.operations

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import pl.info.czerwinski.android.calculator.keys.Key
import pl.info.czerwinski.android.calculator.processor.Processor
import pl.info.czerwinski.android.calculator.processor.Value
import kotlin.test.assertTrue
import kotlin.test.expect

@RunWith(Parameterized::class)
class UnaryOperationTest(val value: Value, val key: Key, val expectedResult: Value) {

	companion object {
		@JvmStatic
		@Parameterized.Parameters(name = "{1}({0}) = {2}")
		fun data() = listOf(
				arrayOf(Value("123.45"), Key.PLUS_MINUS, Value("-123.45")),
				arrayOf(Value("123"), Key.DECIMAL_POINT, Value("123.")),
				arrayOf(Value("123.45"), Key.NUM_6, Value("123.456")),
				arrayOf(Value("2"), Key.NUM_3, Value("23"))
		)
	}

	@Test
	fun testOperation() {
		expect(expectedResult) { (key.operation as UnaryOperation)(value) }
	}
}

@RunWith(Parameterized::class)
class BinaryOperationTest(val x: Value, val y: Value, val key: Key, val expectedResult: Value) {

	companion object {
		@JvmStatic
		@Parameterized.Parameters(name = "{2}({0}, {1}) = {3}")
		fun data() = listOf(
				arrayOf(Value("2"), Value("2"), Key.PLUS, Value("4")),
				arrayOf(Value("2"), Value("-2"), Key.MINUS, Value("4")),
				arrayOf(Value("2"), Value("2"), Key.TIMES, Value("4")),
				arrayOf(Value("2"), Value(".5"), Key.DIV, Value("4"))
		)
	}

	@Test
	fun testOperation() {
		expect(expectedResult) { (key.operation as BinaryOperation)(x, y) }
	}
}

class QueuedOperationTest {

	@Test
	fun testPushUnaryOperation() {
		Processor.clear()
		Key.NUM_2.operation.push()
		expect(1) { Processor.operations.size }
		assertTrue { Processor.operations[0] is UnaryOperation }
		expect("2") { Processor.toString() }
	}

	@Test
	fun testPushBinaryOperation() {
		Processor.clear()
		Key.NUM_2.operation.push()
		Key.PLUS.operation.push()
		expect(2) { Processor.operations.size }
		assertTrue { Processor.operations[0] is UnaryOperation }
		assertTrue { Processor.operations[1] is BinaryOperation }
		expect("2+0") { Processor.toString() }
	}
}
