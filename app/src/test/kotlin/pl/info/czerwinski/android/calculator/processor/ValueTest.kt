package pl.info.czerwinski.android.calculator.processor

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.expect

class ValueTest {

	@Test
	fun testFromString() {
		expect("123") { Value("123").text }
	}

	@Test
	fun testFromInt() {
		expect("123") { Value(123).text }
	}

	@Test
	fun testFromLong() {
		expect("123") { Value(123L).text }
	}

	@Test
	fun testFromFloat() {
		expect("123") { Value(123.0f).text }
	}

	@Test
	fun testFromDouble() {
		expect("123") { Value(123.0).text }
	}

	@Test
	fun testFromFloatFractional() {
		expect("123.4") { Value(123.4f).text }
	}

	@Test
	fun testFromDoubleFractional() {
		expect("123.4") { Value(123.4).text }
	}

	@Test
	fun testToOperator() {
		expect("-123.45") { Value(-123.45).toOperation()(Value(1)).text }
	}

	@Test
	fun testToDouble() {
		expect(-123.4) { Value("-123.4").toDouble() }
	}

	@Test
	fun testToDoubleEmpty() {
		expect(0.0) { Value.EMPTY.toDouble() }
	}

	@Test
	fun testToString() {
		expect("\u2212123.4") { Value("-123.4").toString() }
	}

	@Test
	fun testToStringEmpty() {
		expect("0") { Value.EMPTY.toString() }
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
		expect(expectedResult) { value + digit }
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
				arrayOf(Value("-54."), Value("54.")),
				arrayOf(Value("0"), Value("-0")),
				arrayOf(Value(""), Value("-"))
		)
	}

	@Test
	fun testUnaryMinus() {
		expect(expectedResult) { -value }
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
		expect(expectedResult) { v1 + v2 }
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
		expect(expectedResult) { v1 - v2 }
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
		expect(expectedResult) { v1 * v2 }
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
		expect(expectedResult) { v1 / v2 }
	}
}
