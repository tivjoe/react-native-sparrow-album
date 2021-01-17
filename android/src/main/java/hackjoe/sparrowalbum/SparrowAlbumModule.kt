package hackjoe.sparrowalbum

import android.widget.Toast
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class SparrowAlbumModule(private var reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    companion object {
        private const val DURATION_SHORT_KEY = "SHORT"
        private const val DURATION_LONG_KEY = "LONG"
    }

    override fun getName(): String {
        return "SparrowAlbum"
    }

    override fun getConstants(): MutableMap<String, Any> {
        val constants: MutableMap<String, Any> = mutableMapOf()
        constants[DURATION_SHORT_KEY] = Toast.LENGTH_SHORT
        constants[DURATION_LONG_KEY] = Toast.LENGTH_LONG
        return constants
    }

    @ReactMethod
    fun show(message: String?, duration: Int) {
        Toast.makeText(reactApplicationContext, message, duration).show()
    }
}