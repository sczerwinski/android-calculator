package pl.info.czerwinski.android.calculator.processor

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.expect

@RunWith(Parameterized::class)
class FloatIsIntTest(val value: Float, val expectedResult: Boolean) {

	companion object {
		@JvmStatic
		@Parameterized.Parameters(name = "{0}: {1}")
		fun data() = listOf(
				arrayOf(0f, true),
				arrayOf(1f, true),
				arrayOf(2f, true),
				arrayOf(-1f, true),
				arrayOf(.1f, false),
				arrayOf(-.1f, false),
				arrayOf(1.1f, false),
				arrayOf(Float.POSITIVE_INFINITY, false)
		)
	}

	@Test
	fun testIsInt() {
		expect(expectedResult) { value.isInt() }
	}
}

@RunWith(Parameterized::class)
class DoubleIsLongTest(val value: Double, val expectedResult: Boolean) {

	companion object {
		@JvmStatic
		@Parameterized.Parameters(name = "{0}: {1}")
		fun data() = listOf(
				arrayOf(0.0, true),
				arrayOf(1.0, true),
				arrayOf(2.0, true),
				arrayOf(-1.0, true),
				arrayOf(.1, false),
				arrayOf(-.1, false),
				arrayOf(1.1, false),
				arrayOf(Double.NEGATIVE_INFINITY, false)
		)
	}

	@Test
	fun testIsLong() {
		expect(expectedResult) { value.isLong() }
	}
}
