package pl.info.czerwinski.android.calculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import pl.info.czerwinski.android.calculator.processor.Processor

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		setSupportActionBar(toolbar)
		updateLCD()
		numeric_keypad?.onStateChanged = {
			updateLCD()
		}
	}

	private fun updateLCD() {
		lcd.text = Processor.toString()
	}
}
