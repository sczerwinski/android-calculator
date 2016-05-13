package pl.info.czerwinski.android.calculator

import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(21))
class MainActivityTest {

	val activity: MainActivity by lazy { Robolectric.setupActivity(MainActivity::class.java) }

	@Test
	fun activityShouldBeCreated() {
		assertNotNull(activity)
		assertNotNull(activity.toolbar)
	}
}