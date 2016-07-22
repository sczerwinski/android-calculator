package pl.info.czerwinski.android.calculator

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertNotNull

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(21))
class MainActivityTest {

	val activity: MainActivity by lazy { Robolectric.setupActivity(MainActivity::class.java) }

	@Test
	fun activityShouldBeCreated() {
		assertNotNull(activity)
		assertNotNull(activity.lcd)
	}
}
