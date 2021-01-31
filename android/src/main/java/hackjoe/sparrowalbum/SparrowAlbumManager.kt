package hackjoe.sparrowalbum

import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext

class SparrowAlbumManager() : SimpleViewManager<AlbumView>() {

    companion object {
        private const val REACT_CLASS = "RCTSparrowAlbum"
    }

    override fun getName(): String {
        return REACT_CLASS
    }

    override fun createViewInstance(reactContext: ThemedReactContext): AlbumView {
        return AlbumView(reactContext)
    }

}
