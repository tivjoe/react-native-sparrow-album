package hackjoe.sparrowalbum

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerControlView
import com.google.android.exoplayer2.ui.PlayerView

class PreviewVideoActivity : AppCompatActivity() {

    private lateinit var videoUri: Uri
    private lateinit var player: SimpleExoPlayer
    private lateinit var playerView: PlayerView
    private lateinit var playerControlView: PlayerControlView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview_video)

        // 从被选中的媒体
        videoUri = AlbumDataManager.getSelectedMediasList()[0].uri
        // 初始化视图
        playerView = findViewById(R.id.player_view)
        playerControlView = findViewById(R.id.player_control_view)
        player = SimpleExoPlayer.Builder(this).build()
        initialPlayerView()
    }

    private fun initialPlayerView() {
        playerView.player = player
        playerView.controllerAutoShow = true
        playerView.controllerHideOnTouch = true
        playerView.controllerShowTimeoutMs = 1200
        playerControlView.player = player
        playerControlView.showTimeoutMs = 1200
        player.setMediaItem(MediaItem.fromUri(videoUri))
        player.prepare()
    }

}
