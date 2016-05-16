package pl.info.czerwinski.android.calculator.widget

import android.content.Context
import android.util.AttributeSet
import pl.info.czerwinski.android.calculator.R

class NumericKeypad : Keypad {

	init {
		colsCount = 4
		rowsCount = 5
	}

	constructor(context: Context?) : this(context, null, 0)
	constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
	constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

	override fun buttonDescriptors(): Array<ButtonDescriptor> {
		return arrayOf(
				ButtonDescriptor(ButtonType.SPECIAL, R.string.button_clear),
				ButtonDescriptor(ButtonType.OPERATION, R.string.button_times),
				ButtonDescriptor(ButtonType.OPERATION, R.string.button_div),
				ButtonDescriptor(ButtonType.SPECIAL, R.string.button_back),

				ButtonDescriptor(ButtonType.NUMERIC, R.string.button_7),
				ButtonDescriptor(ButtonType.NUMERIC, R.string.button_8),
				ButtonDescriptor(ButtonType.NUMERIC, R.string.button_9),
				ButtonDescriptor(ButtonType.OPERATION, R.string.button_brackets),

				ButtonDescriptor(ButtonType.NUMERIC, R.string.button_4),
				ButtonDescriptor(ButtonType.NUMERIC, R.string.button_5),
				ButtonDescriptor(ButtonType.NUMERIC, R.string.button_6),
				ButtonDescriptor(ButtonType.OPERATION, R.string.button_minus),

				ButtonDescriptor(ButtonType.NUMERIC, R.string.button_1),
				ButtonDescriptor(ButtonType.NUMERIC, R.string.button_2),
				ButtonDescriptor(ButtonType.NUMERIC, R.string.button_3),
				ButtonDescriptor(ButtonType.OPERATION, R.string.button_plus),

				ButtonDescriptor(ButtonType.NUMERIC, R.string.button_plus_minus),
				ButtonDescriptor(ButtonType.NUMERIC, R.string.button_0),
				ButtonDescriptor(ButtonType.NUMERIC, R.string.button_decimal),
				ButtonDescriptor(ButtonType.SPECIAL, R.string.button_exec)
		)
	}
}
