package pl.info.czerwinski.android.calculator.widget

import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import pl.info.czerwinski.android.calculator.R
import pl.info.czerwinski.android.calculator.processor.operations.*

enum class Key(@StringRes val stringId: Int, val type: KeyType, val operation: Operation) {

	NUM_0(R.string.button_0, KeyType.NUMERIC, UnaryOperation { it + '0' }),
	NUM_1(R.string.button_1, KeyType.NUMERIC, UnaryOperation { it + '1' }),
	NUM_2(R.string.button_2, KeyType.NUMERIC, UnaryOperation { it + '2' }),
	NUM_3(R.string.button_3, KeyType.NUMERIC, UnaryOperation { it + '3' }),
	NUM_4(R.string.button_4, KeyType.NUMERIC, UnaryOperation { it + '4' }),
	NUM_5(R.string.button_5, KeyType.NUMERIC, UnaryOperation { it + '5' }),
	NUM_6(R.string.button_6, KeyType.NUMERIC, UnaryOperation { it + '6' }),
	NUM_7(R.string.button_7, KeyType.NUMERIC, UnaryOperation { it + '7' }),
	NUM_8(R.string.button_8, KeyType.NUMERIC, UnaryOperation { it + '8' }),
	NUM_9(R.string.button_9, KeyType.NUMERIC, UnaryOperation { it + '9' }),

	DECIMAL_POINT(R.string.button_decimal, KeyType.NUMERIC, UnaryOperation { it + '.' }),
	PLUS_MINUS(R.string.button_plus_minus, KeyType.NUMERIC, UnaryOperation { -it }),

	PLUS(R.string.button_plus, KeyType.OPERATION, BinaryOperation("+") { x, y -> x + y }),
	MINUS(R.string.button_minus, KeyType.OPERATION, BinaryOperation("\u2212") { x, y -> x - y }),
	TIMES(R.string.button_times, KeyType.OPERATION, BinaryOperation("\u00D7") { x, y -> x * y }),
	DIV(R.string.button_div, KeyType.OPERATION, BinaryOperation("\u00F7") { x, y -> x / y }),

	BRACKETS(R.string.button_brackets, KeyType.OPERATION, DummyOperation()),

	CLEAR(R.string.button_clear, KeyType.SPECIAL, ClearOperation()),
	BACK(R.string.button_back, KeyType.SPECIAL, BackOperation()),

	EXEC(R.string.button_exec, KeyType.SPECIAL, ExecOperation()),
}

enum class KeyType(@LayoutRes val layoutId: Int) {
	NUMERIC(R.layout.button_numeric),
	OPERATION(R.layout.button_operation),
	SPECIAL(R.layout.button_special),
}