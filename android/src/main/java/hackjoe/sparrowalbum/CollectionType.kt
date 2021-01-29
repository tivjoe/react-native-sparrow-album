package hackjoe.sparrowalbum

import android.net.Uri

/**
 * @file 类型,接口等声明汇总
 * 该库所有数据类型，接口都声明在这里
 */

/**
 * 相册item的数据模型
 */
data class AlbumItemData(
        val id: Long,
        val uri: Uri,
        val name: String,
        val width: Int,
        val height: Int,
        val mimeType: String,
        val duration: Int?,
        val createDate: Long
)
