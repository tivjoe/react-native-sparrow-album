package hackjoe.sparrowalbum

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2

class PreviewImageActivity : AppCompatActivity() {

    private val imagesUri: MutableList<Uri> = mutableListOf()
    private lateinit var previewImageAdapter: PreviewImageAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview_image)

        // 获取穿过来的图片数组
        val selectedMedias = AlbumDataManager.getSelectedMedias()
        selectedMedias.forEach {
            imagesUri.add(it.value.uri)
        }
        // 初始化工作
        initialText()
        initialViewPager()
    }

    private fun initialViewPager() {
        previewImageAdapter = PreviewImageAdapter(imagesUri)
        viewPager = findViewById(R.id.pager)
        viewPager.adapter = previewImageAdapter
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val currentPositionText = findViewById<TextView>(R.id.current_position)
                currentPositionText.text = (position + 1).toString()
            }
        })
    }

    private fun initialText() {
        val currentPositionText = findViewById<TextView>(R.id.current_position)
        val countImageText = findViewById<TextView>(R.id.count_image)
        currentPositionText.text = "1"
        countImageText.text = imagesUri.size.toString()
    }

}
