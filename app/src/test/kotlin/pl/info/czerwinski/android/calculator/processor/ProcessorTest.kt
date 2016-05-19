package pl.info.czerwinski.android.calculator.processor

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import pl.info.czerwinski.android.calculator.keys.Key
import kotlin.test.expect

@RunWith(Parameterized::class)
class ProcessorOutputTest(val keys: List<Key>, val expectedOutput: String) {

	companion object {
		@JvmStatic
		@Parameterized.Parameters(name = "{0}: {1}")
		fun data() = listOf(
				arrayOf(listOf(Key.NUM_1, Key.NUM_2, Key.DECIMAL_POINT, Key.NUM_5), "12.5"),
				arrayOf(listOf(Key.NUM_1, Key.NUM_2, Key.DECIMAL_POINT, Key.NUM_5, Key.PLUS, Key.NUM_5, Key.NUM_2, Key.DIV, Key.NUM_2), "12.5+52\u00F72"),
				arrayOf(listOf(Key.NUM_1, Key.NUM_2, Key.DECIMAL_POINT, Key.NUM_5, Key.BACK), "12."),
				arrayOf(listOf(Key.BACK), "0"),
				arrayOf(listOf(Key.NUM_1, Key.NUM_2, Key.DECIMAL_POINT, Key.NUM_5, Key.CLEAR), "0"),
				arrayOf(listOf(Key.NUM_2, Key.PLUS, Key.NUM_2, Key.EXEC), "4"),
				arrayOf(listOf<Key>(), "0")
		)
	}

	@Before
	fun clearProcessor() {
		Processor.clear()
	}

	@Test
	fun testOutput() {
		keys.map { it.operation.push() }
		expect(expectedOutput) { Processor.toString() }
	}
}
