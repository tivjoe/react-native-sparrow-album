package hackjoe.sparrowalbum

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule

class SparrowAlbumModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    companion object {
        private const val REACT_CLASS = "SparrowAlbum"
    }

    override fun getName(): String {
        return REACT_CLASS
    }

    override fun getConstants(): MutableMap<String, Any> {
        val constants: MutableMap<String, Any> = mutableMapOf()
        return constants
    }
}