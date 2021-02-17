package hackjoe.sparrowalbum

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AlbumView(context: Context) : RecyclerView(context) {

    private val albumDataManager: AlbumDataManager = AlbumDataManager(context)
    private val viewAdapter: Adapter<*>
    private val viewManager: LayoutManager

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
