package work.vuong.screenshotdetector

import android.os.Environment
import android.os.FileObserver
import java.io.File

class ScreenShotListener {

    var onScreenShotListener: OnScreenShotListener? = null

    private val fileObserver = object: FileObserver(getScreenshotPath(), CREATE) {
        override fun onEvent(event: Int, path: String?) {
            path?.let { onScreenShotListener?.onScreenShot(path) }
        }
    }

    init {
        fileObserver.startWatching()
    }

    private fun getScreenshotPath() = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Screenshots").path

    interface OnScreenShotListener{
        fun onScreenShot(fileName: String)
    }

}