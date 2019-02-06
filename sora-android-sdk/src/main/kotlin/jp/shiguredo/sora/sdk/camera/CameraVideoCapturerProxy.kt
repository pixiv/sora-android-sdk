package jp.shiguredo.sora.sdk.camera

import android.content.Context
import org.webrtc.CameraVideoCapturer
import org.webrtc.CapturerObserver
import org.webrtc.SurfaceTextureHelper

class CameraVideoCapturerProxy(private val capturer: CameraVideoCapturer,
                               private val isScreencast: Boolean = false): CameraVideoCapturer {
    override fun startCapture(width: Int, height: Int, framerate: Int) {
        capturer.startCapture(width, height, framerate)
    }

    override fun stopCapture() {
        capturer.stopCapture()
    }

    override fun changeCaptureFormat(width: Int, height: Int, framerate: Int) {
        capturer.changeCaptureFormat(width, height, framerate)
    }

    override fun switchCamera(handler: CameraVideoCapturer.CameraSwitchHandler?) {
        capturer.switchCamera(handler)
    }

    override fun isScreencast(): Boolean {
        return isScreencast
    }

    override fun initialize(surfaceTextureHelper: SurfaceTextureHelper?, context: Context?,
                            capturerObserver: CapturerObserver?) {
        capturer.initialize(surfaceTextureHelper, context, capturerObserver)
    }

    override fun dispose() {
        capturer.dispose()
    }
}