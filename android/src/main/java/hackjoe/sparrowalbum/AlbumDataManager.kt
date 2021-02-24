package hackjoe.sparrowalbum

import android.content.Context

class AlbumDataManager(context: Context) {

    /** 相册中所有媒体列表 */
    var mediaList: MutableList<AlbumItemData> = mutableListOf()

    companion object {
        /** 在相册中被选中的媒体集合 */
        private var selectedMedias: MutableMap<Long, AlbumItemData> = mutableMapOf()

        /**
         * 获取被选中的媒体数据
         */
        fun getSelectedMedias(): MutableMap<Long, AlbumItemData> {
            return selectedMedias
        }
    }

    init {
        // 取出本地所有图片和视频并进行排序
        mediaList.addAll(MediaUtil.getLocalPhotos(context))
        mediaList.addAll(MediaUtil.getLocalVideo(context))
        mediaList.sortBy { albumItemData -> albumItemData.id }
    }

    /**
     * 选择媒体
     * @param position mediaList的索引
     * 如果被选中媒体数据发生变化则返回true
     */
    fun selectMedia(position: Int): Boolean {
        val key = mediaList[position].id
        // 当前要添加的media和已经选中media类型不一致的话，不添加
        if (getSelectedMediaType() != "empty") {
            if (MediaUtil.getMediaType(mediaList[position].mimeType) != getSelectedMediaType()) {
                return false
            }
        }
        if (containsMedia(position)) {
            selectedMedias.remove(key)
        } else {
            if (overfullMedia()) return false
            selectedMedias[key] = mediaList[position]
        }
        return true
    }

    /**
     * 已经超过最大选中数量，返回true
     */
    fun overfullMedia(): Boolean {
        // 只允许选中一个视频
        if (getSelectedMediaType() == "video") {
            return true
        }
        return selectedMedias.size >= 4
    }

    /**
     * 已经选中了该media,返回true
     * @param position mediaList的索引
     */
    fun containsMedia(position: Int): Boolean {
        val key = mediaList[position].id
        return selectedMedias.containsKey(key)
    }

    /**
     * 获取选中媒体map的存储类型
     */
    fun getSelectedMediaType(): String {
        return if (selectedMedias.isEmpty()) {
            "empty"
        } else {
            var mediaType = ""
            for ((ket, value) in selectedMedias) {
                mediaType = MediaUtil.getMediaType(value.mimeType)
                break
            }
            mediaType
        }
    }

}