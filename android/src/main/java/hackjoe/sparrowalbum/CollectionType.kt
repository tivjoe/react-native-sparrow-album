package hackjoe.sparrowalbum

import android.net.Uri
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap

/**
 * @file 类型,接口等声明汇总
 * 该库所有数据类型，接口都声明在这里
 */

/**
 * 相册item的数据模型
 */
data class AlbumItemData(
        val id: Long,
        var uri: Uri,
        val name: String,
        val width: Int,
        val height: Int,
        val mimeType: String,
        val duration: Int?,
        val createDate: Long
)

/**
 * Rn事件名
 */
object RnEvent {
    const val EVENT_ON_CHANGE_SELECTED_MEDIAS = "sparrow_album_change_selected_medias"
}

/**
 * 返回给rn的item数据模型
 */
fun albumItemDataToRnObject(item: AlbumItemData): WritableMap {
    return Arguments.createMap().apply {
        putInt("id", item.id.toInt())
        putString("uri", item.uri.toString())
        putString("name", item.name)
        putInt("width", item.width)
        putInt("height", item.height)
        putString("mimeType", item.mimeType)
        item.duration?.let { putInt("duration", it) }
        putInt("createDate", item.createDate.toInt())
    }
}