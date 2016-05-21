package pl.info.czerwinski.android.calculator.processor.operations

import org.junit.Before
import org.junit.Test
import pl.info.czerwinski.android.calculator.keys.Key
import pl.info.czerwinski.android.calculator.processor.Processor
import kotlin.test.expect

class SpecialOperationsTest {

	@Before
	fun clearProcessor() {
		Processor.clear()
	}

	@Test
	fun testBackOperation() {
		listOf(Key.NUM_1, Key.NUM_2, Key.NUM_3)
				.map { it.operation.push() }
		BackOperation().push()
		expect(2) { Processor.size() }
		expect("12") { Processor.toString() }
	}

	@Test
	fun testClearOperation() {
		listOf(Key.NUM_1, Key.NUM_2, Key.NUM_3)
				.map { it.operation.push() }
		ClearOperation().push()
		expect(0) { Processor.size() }
	}

	@Test
	fun testExecOperation() {
		listOf(Key.NUM_2, Key.PLUS, Key.NUM_2)
				.map { it.operation.push() }
		ExecOperation().push()
		expect(1) { Processor.size() }
		expect("4") { Processor.toString() }
	}
}
