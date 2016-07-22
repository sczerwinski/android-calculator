package pl.info.czerwinski.android.calculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.TextView
import org.jetbrains.anko.*
import pl.info.czerwinski.android.calculator.processor.Processor
import pl.info.czerwinski.android.calculator.widget.numericKeypad
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

	var lcd: TextView by Delegates.notNull<TextView>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		relativeLayout {
			lcd = textView(text = R.string.button_0) {
				id = 1
				textColor = 0x000000.opaque
				textSize = 24f
				backgroundColor = 0xDCEDC8.opaque
				horizontalPadding = dip(16)
				verticalPadding = dip(16)
				gravity = Gravity.RIGHT
			}.lparams(width = matchParent) {
				alignParentTop()
			}
			numericKeypad {
				horizontalPadding = dip(8)
				verticalPadding = dip(8)
				onStateChanged {
					updateLCD()
				}
			}.lparams(width = matchParent, height = matchParent) {
				below(lcd)
			}
		}
		updateLCD()
	}

	private fun updateLCD() {
		lcd.text = Processor.toString()
	}
}
