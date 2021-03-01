package hackjoe.sparrowalbum

import com.facebook.react.bridge.*
import java.lang.Exception

class SparrowAlbumModule(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    companion object {
        private const val REACT_CLASS = "SparrowAlbum"
    }

    override fun getName(): String {
        return REACT_CLASS
    }

    override fun getConstants(): MutableMap<String, Any> {
        return mutableMapOf()
    }

    @ReactMethod
    fun previewSelectedMedias() {
        MediaUtil.previewMedias(reactContext, AlbumDataManager.getSelectedMediasList())
    }

    @ReactMethod
    fun cropImage(imageUri: String) {
        MediaUtil.cropImage(reactContext, imageUri)
    }

    @ReactMethod
    fun confirmSelected(promise: Promise) {
        try {
            val returnList = mutableListOf<WritableMap>()
            AlbumDataManager.getSelectedMediasList().forEach {
                returnList.add(albumItemDataToRnObject(it))
            }
            promise.resolve(Arguments.makeNativeArray(returnList))
        } catch (e: Exception) {
            promise.reject("Error", e);
        }
    }

}