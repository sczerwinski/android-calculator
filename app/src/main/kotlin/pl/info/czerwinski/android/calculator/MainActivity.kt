package pl.info.czerwinski.android.calculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

	val toolbar: Toolbar by lazy { findViewById(R.id.toolbar) as Toolbar }
	val lcd: TextView by lazy { findViewById(R.id.lcd) as TextView }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		setSupportActionBar(toolbar)
	}
}
