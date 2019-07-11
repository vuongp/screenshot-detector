package work.vuong.screenshotdetector

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), ScreenShotListener.OnScreenShotListener {

    private var screenshotObserver: ScreenShotListener = ScreenShotListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermissionForReadExternalStorage()
        screenshotObserver.onScreenShotListener = this
    }

    @Throws(Exception::class)
    fun requestPermissionForReadExternalStorage() {
        try {
            ActivityCompat.requestPermissions(
                this, arrayOf(READ_EXTERNAL_STORAGE),
                1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }

    }

    override fun onScreenShot(fileName: String) {
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(this@MainActivity, fileName, Toast.LENGTH_LONG).show()
        }
    }
}
