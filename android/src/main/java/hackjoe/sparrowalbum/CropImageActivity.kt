package hackjoe.sparrowalbum

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.theartofdev.edmodo.cropper.CropImageView

class CropImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop_image)
        initialCropImageView()
    }

    private fun initialCropImageView() {
        val imageUri = Uri.parse(intent.getStringExtra("IMAGE_URI"))
        val cropImageView = findViewById<CropImageView>(R.id.cropImageView)
        cropImageView.setImageUriAsync(imageUri)
        cropImageView.setAspectRatio(1, 1)
        cropImageView.setFixedAspectRatio(true)
        cropImageView.isAutoZoomEnabled = true
        cropImageView.isShowProgressBar = true
        cropImageView.setOnCropImageCompleteListener { view, result ->
            val croppedUri = MediaUtil.saveBitmapToAlbum(this, result.bitmap)
            if (croppedUri!=null){
                finish()
            }
        }
    }

    fun rotateImage(view: View) {
        val cropImageView = findViewById<CropImageView>(R.id.cropImageView)
        cropImageView.rotateImage(90)
    }

    fun confirmCrop(view: View) {
        val cropImageView = findViewById<CropImageView>(R.id.cropImageView)
        cropImageView.getCroppedImageAsync()
    }
}