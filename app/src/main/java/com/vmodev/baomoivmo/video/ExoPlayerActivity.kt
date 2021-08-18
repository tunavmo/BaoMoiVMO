package com.vmodev.baomoivmo.video

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import android.view.View
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.Util
import com.vmodev.baomoivmo.R
import kotlinx.android.synthetic.main.activity_exo_player.*

class ExoPlayerActivity : AppCompatActivity() {
    private var player: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var currentWindows = 0
    private var playbackPosition: Long = 0
    var url:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exo_player)
        url = intent.getStringExtra("URL")
        initPlayer(url)
    }

    private fun initPlayer(url:String?) {
        player = SimpleExoPlayer.Builder(this).build()
        videoView.player = player
        object: YouTubeExtractor(this){
            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>?,
                videoMeta: VideoMeta?
            ) {
                if(ytFiles!=null)
                {
                    val itag = 137
                    val audioTag = 140
                    val videoUrl = ytFiles[itag].url
                    val audioUrl = ytFiles[audioTag].url

                    val audioSource : MediaSource = ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory()).createMediaSource(
                        MediaItem.fromUri(audioUrl))

                        val videoSource : MediaSource = ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory()).createMediaSource(
                        MediaItem.fromUri(videoUrl))

                    player!!.setMediaSource(MergingMediaSource(true, videoSource, audioSource), true)
                    player!!.prepare()
                    player!!.playWhenReady = playWhenReady
                    player!!.seekTo(currentWindows, playbackPosition)

                }
            }

        }.extract(url, false, true)
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24)
            initPlayer(url)
    }

    override fun onResume() {
        super.onResume()
        if (Util.SDK_INT < 24 || player == null) {
            initPlayer(url)
            hideSystemUI()
        }
    }

    private fun hideSystemUI() {
        videoView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LOW_PROFILE or
                        View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                )
    }

    override fun onPause() {
        if(Util.SDK_INT<24) releasePlayer()
        super.onPause()

    }

    override fun onStop() {
        if(Util.SDK_INT<24) releasePlayer()
        super.onStop()
    }

    private fun releasePlayer() {
        if(player==null)
        {
            playWhenReady = player!!.playWhenReady
            playbackPosition = player!!.currentPosition
            currentWindows = player!!.currentWindowIndex
            player!!.release()
            player= null
        }
    }
}