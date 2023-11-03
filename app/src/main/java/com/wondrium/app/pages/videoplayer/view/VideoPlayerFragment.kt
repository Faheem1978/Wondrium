package com.wondrium.app.pages.videoplayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.wondrium.app.databinding.FragmentVideoPlayerBinding


class VideoPlayerFragment : Fragment() {

    private val START_INDEX: String = "startIndex"
    private val START_INDEX_POS: String = "startIndexPos"
    private var startItemIndex = C.INDEX_UNSET
    private var startItemPosition = 0L
    private var player: ExoPlayer? = null
    private var _binding: FragmentVideoPlayerBinding? = null
    private val binding get() = _binding!!

    val VIDEO_URL =
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentVideoPlayerBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            startItemIndex = savedInstanceState.getInt(START_INDEX)
            startItemPosition = savedInstanceState.getLong(START_INDEX_POS)
        }
        initPlayer()
        player?.play()

    }

    override fun onStart() {
        super.onStart()
        resumePlayer()
    }

    private fun resumePlayer() {
        initPlayer()
        binding.playerView.onResume()

    }


    override fun onResume() {
        super.onResume()
        initPlayer()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        updateStartPosition()
        outState.putInt(START_INDEX, startItemIndex);
        outState.putInt(START_INDEX_POS, startItemIndex);

    }

    private fun initPlayer() {
        if (player == null) {
            player = context?.let { ExoPlayer.Builder(it).build() }
            binding.playerView.player = player
            val mediaItem = MediaItem.fromUri(VIDEO_URL)
            player?.setMediaItem(mediaItem)
            player?.prepare()
            if (startItemIndex != C.INDEX_UNSET) {
                player!!.seekTo(startItemIndex, startItemPosition)
            }
        }

    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()

    }

    private fun releasePlayer() {
        updateStartPosition()
        binding.playerView.onPause()
        player?.release()
        player = null
        binding.playerView.player = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    protected fun clearStartPosition() {
        startItemIndex = C.INDEX_UNSET
        startItemPosition = C.TIME_UNSET
    }

    private fun updateStartPosition() {
        if (player != null) {
            startItemIndex = player!!.currentMediaItemIndex
            startItemPosition = Math.max(0, player!!.contentPosition)
        }
    }
}