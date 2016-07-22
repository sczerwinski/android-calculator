package pl.info.czerwinski.android.calculator.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import pl.info.czerwinski.android.calculator.keys.Key

abstract class Keypad : GridLayout {

	private var onStateChangedCallback: (() -> Unit) = {}

	constructor(context: Context?) : this(context, null, 0)
	constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
	constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
		val layoutInflater = LayoutInflater.from(context)
		for (key in keys()) {
			val button = layoutInflater.inflate(key.type.layoutId, this, false) as Button?
			button?.setText(key.stringId)
			button?.setOnClickListener {
				key.operation.push()
				onStateChangedCallback.invoke()
			}
			addView(button)
		}
	}

	fun onStateChanged(callback: () -> Unit) {
		onStateChangedCallback = callback
	}

	abstract fun keys(): Array<Key>
}
