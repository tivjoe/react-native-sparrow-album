package hackjoe.sparrowalbum

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore

/**
 * @file 媒体工具集
 */
object MediaUtil {

    /**
     * 获取本地图片
     * @param context
     * @return 返回一个图片list
     */
    fun getLocalPhotos(context: Context): MutableList<AlbumItemData> {
        val photoList: MutableList<AlbumItemData> = mutableListOf()
        context.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null,
                null
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val mimeTypeColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE)
            val widthColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)
            val heightColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT)
            val createColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)
            while (cursor.moveToNext()) {
                val mediaId = cursor.getLong(idColumn)
                val item = AlbumItemData(
                        id = mediaId,
                        uri = ContentUris.withAppendedId(
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                mediaId
                        ),
                        width = cursor.getInt(widthColumn),
                        height = cursor.getInt(heightColumn),
                        name = cursor.getString(nameColumn),
                        mimeType = cursor.getString(mimeTypeColumn),
                        duration = null,
                        createDate = cursor.getLong(createColumn)
                )
                photoList.add(item)
            }
        }
        return photoList
    }

    /**
     * 获取本地所有视频
     * @param context
     * @return 返回一个视频list
     */
    fun getLocalVideo(context: Context): MutableList<AlbumItemData> {
        val videoList: MutableList<AlbumItemData> = mutableListOf()
        context.contentResolver.query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null,
                null
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
            val mimeTypeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE)
            val widthColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.WIDTH)
            val heightColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.HEIGHT)
            val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)
            val createColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_ADDED)
            while (cursor.moveToNext()) {
                val mediaId = cursor.getLong(idColumn)
                val item = AlbumItemData(
                        id = mediaId,
                        uri = ContentUris.withAppendedId(
                                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                                mediaId
                        ),
                        name = cursor.getString(nameColumn),
                        width = cursor.getInt(widthColumn),
                        height = cursor.getInt(heightColumn),
                        mimeType = cursor.getString(mimeTypeColumn),
                        duration = cursor.getInt(durationColumn),
                        createDate = cursor.getLong(createColumn),
                )
                videoList.add(item)
            }
        }
        return videoList
    }

    /**
     * 获取媒体类型
     */
    fun getMediaType(mimeType: String) = mimeType.split("/")[0]

    /**
     * 预览所有被选中媒体文件
     * JS端可以直接调用该方法
     */
    fun previewMedias(context: Context, medias: List<AlbumItemData>) {
        if (medias.isNotEmpty()) {
            val intent = if (getMediaType(medias[0].mimeType) == "image") {
                Intent(context, PreviewImageActivity::class.java).apply {}
            } else {
                Intent(context, PreviewVideoActivity::class.java).apply {}
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    /**
     * 剪裁图片
     */
    fun cropImage(context: Context, imageUri: String) {
        val intent = Intent(context, CropImageActivity::class.java).apply {
            putExtra("IMAGE_URI", imageUri)
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    /**
     * 将Bitmap保存到相册
     */
    fun saveBitmapToAlbum(context: Context, bitmap: Bitmap): Uri? {
        val imageDetails = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, System.currentTimeMillis().toString())
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }
        val imageContentUri = context.contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                imageDetails
        )
        if (imageContentUri != null) {
            context.contentResolver.openOutputStream(imageContentUri).use {
                it ?: return null
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            }
            imageDetails.clear()
            context.contentResolver.update(imageContentUri, imageDetails, null, null)
            return imageContentUri
        }
        return null
    }

    /**
     * TODO 压缩图片
     */
    fun compressImage() {

    }

    /**
     * TODO 压缩视频
     */
    fun compressVideo() {

    }

}