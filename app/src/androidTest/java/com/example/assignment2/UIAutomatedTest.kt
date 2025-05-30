package com.example.assignment2
import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import androidx.test.runner.AndroidJUnit4
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import junit.framework.TestCase.assertTrue
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith


private const val MY_PACKAGE = "com.example.assignment2"
private const val LAUNCH_TIMEOUT = 5000L

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class UIAutomatedTest {

    private lateinit var device: UiDevice

    @Before
    fun startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Start from the home screen
        device.pressHome()

        // Wait for launcher
        val launcherPackage: String = device.launcherPackageName
        assertThat(launcherPackage, notNullValue())
        device.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT
        )

        // Launch the app
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(
            MY_PACKAGE)?.apply {
            // Clear out any previous instances
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)

        // Wait for the app to appear
        device.wait(
            Until.hasObject(By.pkg(MY_PACKAGE).depth(0)),
            LAUNCH_TIMEOUT
        )

        // Check app has been opened.
        assertTrue(device.hasObject(By.pkg(MY_PACKAGE)))
    }

    @Test
    fun testNavigatingToChallengesActivityFromMain() {
        val timeout = 5000L

        //Navigate to Software Challenges
        val explicitActivityButton: UiObject2? = device.wait(
            Until.findObject(
                By.text("Start Activity Explicitly")
            ),
            timeout
        )
        assertThat(explicitActivityButton, notNullValue())

        if (explicitActivityButton != null) {
            device.performActionAndWait(
                { explicitActivityButton.click() },
                Until.newWindow(),
                timeout
            )

            assertTrue(device.wait(Until.hasObject(By.textContains("Tool support")), timeout))

        }
    }
}