package hackjoe.sparrowalbum

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AlbumAdapter(
        private val context: Context,
        private val albumDataManager: AlbumDataManager
) : RecyclerView.Adapter<AlbumAdapter.AlbumHolder>() {

    class AlbumHolder(itemView: LinearLayout) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.album_item, parent, false) as LinearLayout

        return AlbumHolder(itemView)
    }

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        holder.itemView.setOnClickListener { onClickItemListener(holder.itemView, position) }
        val textView = holder.itemView.findViewById<TextView>(R.id.my_text_view)
        val imageView = holder.itemView.findViewById<ImageView>(R.id.photo)

        textView.text = albumDataManager.mediaList[position].name
        imageView.setImageURI(albumDataManager.mediaList[position].uri)
    }

    override fun getItemCount() = albumDataManager.mediaList.size

    /**
     * item点击事件
     * @param view item实例对象
     * @param position albumData.mediaList索引
     */
    private fun onClickItemListener(view: View, position: Int) {

    }
}