package hackjoe.sparrowalbum

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
        viewAdapter = AlbumAdapter(context, albumDataManager)
        this.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}
