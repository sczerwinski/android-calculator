package pl.info.czerwinski.android.calculator.widget

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import pl.info.czerwinski.android.calculator.R

abstract class Keypad : GridLayout {

	constructor(context: Context?) : this(context, null, 0)
	constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
	constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
		val layoutInflater = LayoutInflater.from(context)
		for ((type, stringRes) in buttonDescriptors()) {
			val button = layoutInflater.inflate(type.layoutRes, this, false) as Button?
			button?.setText(stringRes)
			addView(button)
		}
	}

	abstract fun buttonDescriptors(): Array<ButtonDescriptor>
}

data class ButtonDescriptor(val type: ButtonType, @StringRes val stringRes: Int)

enum class ButtonType(@LayoutRes val layoutRes: Int) {
	NUMERIC(R.layout.button_numeric),
	OPERATION(R.layout.button_operation),
	SPECIAL(R.layout.button_special)
}
