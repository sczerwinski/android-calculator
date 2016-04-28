package pl.info.czerwinski.android.calculator.processor

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import pl.info.czerwinski.android.calculator.widget.Key

class ProcessorToStringTest {

	@Before
	fun clearProcessor() {
		Processor.clear()
	}

	@Test
	fun testSingleNumber() {
		listOf(Key.NUM_1, Key.NUM_2, Key.DECIMAL_POINT, Key.NUM_5)
				.map { it -> it.operation }
				.map { it.push() }
		assertEquals("12.5", Processor.toString());
	}

	@Test
	fun testMultipleNumbers() {
		listOf(Key.NUM_1, Key.NUM_2, Key.DECIMAL_POINT, Key.NUM_5, Key.PLUS, Key.NUM_5, Key.NUM_2, Key.DIV, Key.NUM_2)
				.map { it -> it.operation }
				.map { it.push() }
		assertEquals("12.5+52\u00F72", Processor.toString());
	}
}
