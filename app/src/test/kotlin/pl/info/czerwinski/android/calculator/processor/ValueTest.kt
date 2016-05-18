package pl.info.czerwinski.android.calculator.processor

import org.junit.Assert.assertEquals

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

class ValueTest {

	@Test
	fun testFromString() {
		val text = "123"
		val value = Value(text)
		assertEquals("123", value.text)
	}

	@Test
	fun testFromInt() {
		val v = 123
		val value = Value(v)
		assertEquals("123", value.text)
	}

	@Test
	fun testFromLong() {
		val v = 123L
		val value = Value(v)
		assertEquals("123", value.text)
	}

	@Test
	fun testFromFloat() {
		val v = 123.0f
		val value = Value(v)
		assertEquals("123", value.text)
	}

	@Test
	fun testFromDouble() {
		val v = 123.0
		val value = Value(v)
		assertEquals("123", value.text)
	}

	@Test
	fun testFromFloatFractional() {
		val v = 123.4f
		val value = Value(v)
		assertEquals("123.4", value.text)
	}

	@Test
	fun testFromDoubleFractional() {
		val v = 123.4
		val value = Value(v)
		assertEquals("123.4", value.text)
	}

	@Test
	fun testToOperator() {
		val value = Value(-123.45)
		val operation = value.toOperation()
		val result = operation(Value(1))
		assertEquals("-123.45", result.text)
	}

	@Test
	fun testToDouble() {
		val text = "-123.4"
		val value = Value(text)
		assertEquals(-123.4, value.toDouble(), 0.000000001)
	}

	@Test
	fun testToDoubleEmpty() {
		val value = Value("")
		assertEquals(0.0, value.toDouble(), 0.000000001)
	}

	@Test
	fun testToString() {
		val text = "-123.4"
		val value = Value(text)
		assertEquals("\u2212123.4", value.toString())
	}

	@Test
	fun testToStringEmpty() {
		val value = Value("")
		assertEquals("0", value.toString())
	}
}

@RunWith(Parameterized::class)
class ValuePlusDigitTest(val value: Value, val digit: Char, val expectedResult: Value) {

	companion object {
		@JvmStatic
		@Parameterized.Parameters(name = "{0} + {1} = {2}")
		fun data() = listOf(
				arrayOf(Value("1"), '1', Value("11")),
				arrayOf(Value("-2"), '2', Value("-22")),
				arrayOf(Value("2"), '.', Value("2.")),
				arrayOf(Value("2."), '.', Value("2.")),
				arrayOf(Value("2."), '2', Value("2.2")),
				arrayOf(Value("2.2"), '.', Value("2.2")),
				arrayOf(Value("2.2"), '0', Value("2.20")),
				arrayOf(Value("0"), '2', Value("2")),
				arrayOf(Value("0"), '.', Value("0.")),
				arrayOf(Value("234.50000"), '0', Value("234.500000"))
		)
	}

	@Test
	fun testPlusDigit() {
		val result = value + digit
		assertEquals(expectedResult, result)
	}
}

@RunWith(Parameterized::class)
class ValueUnaryMinusTest(val value: Value, val expectedResult: Value) {

	companion object {
		@JvmStatic
		@Parameterized.Parameters(name = "\u2212({0}) = {1}")
		fun data() = listOf(
				arrayOf(Value("1"), Value("-1")),
				arrayOf(Value("2.3"), Value("-2.3")),
				arrayOf(Value("-2"), Value("2")),
				arrayOf(Value("-3.5"), Value("3.5")),
				arrayOf(Value("725."), Value("-725.")),
				arrayOf(Value("-54."), Value("54."))
		)
	}

	@Test
	fun testUnaryMinus() {
		val result = -value
		assertEquals(expectedResult, result)
	}
}

@RunWith(Parameterized::class)
class ValuePlusTest(val v1: Value, val v2: Value, val expectedResult: Value) {

	companion object {
		@JvmStatic
		@Parameterized.Parameters(name = "{0} + {1} = {2}")
		fun data() = listOf(
				arrayOf(Value("1"), Value("1"), Value("2")),
				arrayOf(Value("2"), Value("2"), Value("4")),
				arrayOf(Value("-2"), Value("2"), Value("0")),
				arrayOf(Value("2"), Value("0.3"), Value("2.3"))
		)
	}

	@Test
	fun testPlus() {
		val result = v1 + v2
		assertEquals(expectedResult, result)
	}
}

@RunWith(Parameterized::class)
class ValueMinusTest(val v1: Value, val v2: Value, val expectedResult: Value) {

	companion object {
		@JvmStatic
		@Parameterized.Parameters(name = "{0} \u2212 {1} = {2}")
		fun data() = listOf(
				arrayOf(Value("1"), Value("1"), Value("0")),
				arrayOf(Value("4"), Value("2"), Value("2")),
				arrayOf(Value("-4"), Value("2"), Value("-6")),
				arrayOf(Value("2"), Value("0.3"), Value("1.7"))
		)
	}

	@Test
	fun testMinus() {
		val result = v1 - v2
		assertEquals(expectedResult, result)
	}
}

@RunWith(Parameterized::class)
class ValueTimesTest(val v1: Value, val v2: Value, val expectedResult: Value) {

	companion object {
		@JvmStatic
		@Parameterized.Parameters(name = "{0} \u00D7 {1} = {2}")
		fun data() = listOf(
				arrayOf(Value("1"), Value("1"), Value("1")),
				arrayOf(Value("2"), Value("2"), Value("4")),
				arrayOf(Value("-2"), Value("2"), Value("-4")),
				arrayOf(Value("2"), Value("0.5"), Value("1")),
				arrayOf(Value("2"), Value("0.3"), Value("0.6")),
				arrayOf(Value("1.25"), Value("0.8"), Value("1"))
		)
	}

	@Test
	fun testTimes() {
		val result = v1 * v2
		assertEquals(expectedResult, result)
	}
}

@RunWith(Parameterized::class)
class ValueDivTest(val v1: Value, val v2: Value, val expectedResult: Value) {

	companion object {
		@JvmStatic
		@Parameterized.Parameters(name = "{0} \u00F7 {1} = {2}")
		fun data() = listOf(
				arrayOf(Value("1"), Value("1"), Value("1")),
				arrayOf(Value("4"), Value("2"), Value("2")),
				arrayOf(Value("-4"), Value("2"), Value("-2")),
				arrayOf(Value("3"), Value("2"), Value("1.5")),
				arrayOf(Value("2"), Value("0.5"), Value("4")),
				arrayOf(Value("2.5"), Value("1.25"), Value("2")),
				arrayOf(Value("1.2"), Value("0.5"), Value("2.4"))
		)
	}

	@Test
	fun testDiv() {
		val result = v1 / v2
		assertEquals(expectedResult, result)
	}
}
