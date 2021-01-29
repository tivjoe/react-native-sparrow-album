package hackjoe.sparrowalbum

import android.content.Context

class AlbumDataManager(context: Context) {

    /** 相册中所有媒体列表 */
    var mediaList: MutableList<AlbumItemData> = mutableListOf()

    /** 在相册中被选中的媒体集合 */
    private var selectedMedias: MutableMap<Long, AlbumItemData> = mutableMapOf()

    init {
        // 取出本地所有图片和视频并进行排序
        mediaList.addAll(MediaUtil.getLocalPhotos(context))
        mediaList.addAll(MediaUtil.getLocalVideo(context))
        mediaList.sortBy { albumItemData -> albumItemData.id }
    }

}