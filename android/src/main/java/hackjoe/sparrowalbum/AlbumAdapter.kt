package hackjoe.sparrowalbum

import android.content.Context
import android.os.CancellationSignal
import android.util.Size
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AlbumAdapter(
        private val context: Context,
        private val albumView: AlbumView,
        private val albumDataManager: AlbumDataManager
) : RecyclerView.Adapter<AlbumAdapter.AlbumHolder>() {

    class AlbumHolder(itemView: LinearLayout) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.album_item, parent, false) as LinearLayout

        return AlbumHolder(itemView)
    }

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        holder.itemView.setOnClickListener { onClickItemListener(position) }
        val textView = holder.itemView.findViewById<TextView>(R.id.my_text_view)
        val imageView = holder.itemView.findViewById<ImageView>(R.id.photo)
        val isContainsMedia = albumDataManager.containsMedia(position)  // 该媒体是否被选中
        val isFullMedia = albumDataManager.overfullMedia() // 当前选中是否超过上限
        // 缩略图
        if (MediaUtil.getMediaType(albumDataManager.mediaList[position].mimeType) == "image") {
            imageView.setImageURI(albumDataManager.mediaList[position].uri)
        } else {
            imageView.setImageBitmap(
                    context.contentResolver.loadThumbnail(
                            albumDataManager.mediaList[position].uri,
                            Size(100, 100),
                            CancellationSignal()
                    )
            )
        }
        // 如果被选中，显示✔
        if (isContainsMedia) {
            imageView.alpha = 0.6f
        } else {
            imageView.alpha = 1f
        }
        // 选择已经满了且未被选择，图片变为透明
        if (isFullMedia && !isContainsMedia) {
            imageView.alpha = 0.6f
        }

        textView.text = albumDataManager.mediaList[position].name
    }

    override fun getItemCount() = albumDataManager.mediaList.size

    /**
     * item点击事件
     * @param position albumDataManager.mediaList索引
     */
    private fun onClickItemListener(position: Int) {
        // 混选
        val isFullMediaBefore = albumDataManager.overfullMedia()  // 在未改变数据源前就已经满了为true
        val selectedMediaTypeBefore = albumDataManager.getSelectedMediaType() //获取已经选中map媒体类型
        // 尝试修改被选中媒体数据源，如果修改成功，则将修改后的被选中媒体整个返回给js端
        if (albumDataManager.selectMedia(position)) {
            albumView.emitEventChangeSelectedMedias()
        }
        if (albumDataManager.overfullMedia() || selectedMediaTypeBefore == "empty" || albumDataManager.getSelectedMediaType() == "empty") {
            notifyDataSetChanged()
        } else {
            // true为代表已经选满了的情况下，用户取消选中一个item，变成未满的情况，则刷新所有item
            if (isFullMediaBefore) {
                notifyDataSetChanged()
            } else {
                notifyItemChanged(position)
            }
        }
    }

}
