package hackjoe.sparrowalbum

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.react.bridge.*
import com.facebook.react.uimanager.events.RCTEventEmitter
import java.util.*

class AlbumView(context: Context) : RecyclerView(context) {

    private val albumDataManager: AlbumDataManager = AlbumDataManager(context)
    private val viewAdapter: Adapter<*>
    private val viewManager: LayoutManager
    private var mRequestedLayout = false // Check https://stackoverflow.com/questions/49371866/recyclerview-wont-update-child-until-i-scroll

    override fun requestLayout() {
        super.requestLayout()
        // We need to intercept this method because if we don't our children will never update
        // Check https://stackoverflow.com/questions/49371866/recyclerview-wont-update-child-until-i-scroll
        if (!mRequestedLayout) {
            mRequestedLayout = true
            post {
                mRequestedLayout = false
                layout(left, top, right, bottom)
                onLayout(false, left, top, right, bottom)
            }
        }
    }

    init {
        viewManager = GridLayoutManager(context, 4)
        viewAdapter = AlbumAdapter(context, this, albumDataManager)
        this.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    // 当被选中媒体数据发生变化时发送事件给js端
    fun emitEventChangeSelectedMedias() {
        val rnSelectedMediasList = mutableListOf<WritableMap>()
        AlbumDataManager.getSelectedMediasList().forEach {
            rnSelectedMediasList.add(albumItemDataToRnObject(it))
        }
        val event: WritableMap = Arguments.createMap().apply {
            putArray("message", Arguments.makeNativeArray(rnSelectedMediasList))
        }
        val reactContext = context as ReactContext
        reactContext.getJSModule(RCTEventEmitter::class.java).receiveEvent(id, RnEvent.EVENT_ON_CHANGE_SELECTED_MEDIAS, event)
    }

}
