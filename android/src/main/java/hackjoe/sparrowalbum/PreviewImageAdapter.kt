package hackjoe.sparrowalbum

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.github.chrisbanes.photoview.PhotoView

class PreviewImageAdapter(private val imagesUri: MutableList<Uri>) : RecyclerView.Adapter<PreviewImageAdapter.PreviewImageHolder>() {

    class PreviewImageHolder(itemView: LinearLayout) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviewImageHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.preview_image_item, parent, false) as LinearLayout

        return PreviewImageHolder(itemView)
    }

    override fun onBindViewHolder(holder: PreviewImageHolder, position: Int) {
        val photoView = holder.itemView.findViewById<PhotoView>(R.id.photo_view)
        photoView.setImageURI(imagesUri[position])
    }

    override fun getItemCount() = imagesUri.size
}