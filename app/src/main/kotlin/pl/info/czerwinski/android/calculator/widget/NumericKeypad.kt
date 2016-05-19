package pl.info.czerwinski.android.calculator.widget

import android.content.Context
import android.util.AttributeSet
import pl.info.czerwinski.android.calculator.keys.Key

class NumericKeypad : Keypad {

	init {
		colsCount = 4
		rowsCount = 5
	}

	constructor(context: Context?) : this(context, null, 0)
	constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
	constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

	override fun keys(): Array<Key> {
		return arrayOf(
				Key.CLEAR, Key.TIMES, Key.DIV, Key.BACK,
				Key.NUM_7, Key.NUM_8, Key.NUM_9, Key.BRACKETS,
				Key.NUM_4, Key.NUM_5, Key.NUM_6, Key.MINUS,
				Key.NUM_1, Key.NUM_2, Key.NUM_3, Key.PLUS,
				Key.PLUS_MINUS, Key.NUM_0, Key.DECIMAL_POINT, Key.EXEC
		)
	}
}
