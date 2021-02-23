package hackjoe.sparrowalbum

import com.facebook.react.common.MapBuilder
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext

class SparrowAlbumManager : SimpleViewManager<AlbumView>() {

    companion object {
        private const val REACT_CLASS = "RCTSparrowAlbum"
    }

    override fun getName(): String {
        return REACT_CLASS
    }

    override fun createViewInstance(reactContext: ThemedReactContext): AlbumView {
        return AlbumView(reactContext)
    }

    override fun getExportedCustomBubblingEventTypeConstants(): MutableMap<String, Any>? {
        return MapBuilder.builder<String, Any>()
                .put(RnEvent.EVENT_ON_CHANGE_SELECTED_MEDIAS, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onChangeSelectedMedias")))
                .build()
    }

}
