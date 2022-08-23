package cn.framework.manager

import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import cn.framework.util.LogUtils
import java.io.IOException

const val MEDIA_STATUS_PLAY = 0X000
const val MEDIA_STATUS_PAUSE = 0X001
const val MEDIA_STATUS_STOP = 0X002
const val H_PROGRESS = 1000

class MediaPlayerManager(var mMediaPlayer: MediaPlayer) {

    private var MEDIA_STATUS = MEDIA_STATUS_STOP

    private var mProgressListener: OnProgressListener? = null

    init {
        mMediaPlayer = MediaPlayer()
    }

    /**
     * 计算进度
     */
    private lateinit var mHandler: Handler

    init {
        mHandler = Handler(Looper.getMainLooper()) { msg ->
            when (msg.what) {
                H_PROGRESS -> {
                    val currentPosition = getCurrentPosition()
                    val position =
                        (currentPosition.toFloat() / getDuration().toFloat() * 100).toInt()
                    mProgressListener?.onProgress(currentPosition, position)

                    mHandler.sendEmptyMessageDelayed(H_PROGRESS, 1000)
                }
            }
            false
        }
    }


    private fun isPlaying() = mMediaPlayer.isPlaying

    fun startPlay(path: String) {
        try {
            mMediaPlayer.reset()
            mMediaPlayer.setDataSource(path)
            mMediaPlayer.prepare()
            mMediaPlayer.start()
            MEDIA_STATUS = MEDIA_STATUS_PLAY
            mHandler.sendEmptyMessage(H_PROGRESS)
        } catch (e: IOException) {
            LogUtils.e(e.toString())
            e.printStackTrace()
        }
    }

    fun pausePlay(path: String) {
        if (isPlaying()) {
            mMediaPlayer.pause()
            MEDIA_STATUS = MEDIA_STATUS_PAUSE
            mHandler.removeMessages(H_PROGRESS)
        }
    }

    fun continuePlay() {
        mMediaPlayer.start()
        MEDIA_STATUS = MEDIA_STATUS_PLAY
    }

    fun stopPlay() {
        mMediaPlayer.stop()
        MEDIA_STATUS = MEDIA_STATUS_STOP
        mHandler.removeMessages(H_PROGRESS)
    }

    private fun getCurrentPosition() = mMediaPlayer.currentPosition

    private fun getDuration() = mMediaPlayer.duration

    fun setLooping(isLooping: Boolean) {
        mMediaPlayer.isLooping = isLooping
    }

    fun seekTo(ms: Int) {
        mMediaPlayer.seekTo(ms)
    }

    fun setOnCompletionListener(listener: MediaPlayer.OnCompletionListener) {
        mMediaPlayer.setOnCompletionListener(listener)
    }

    fun setOnErrorListener(listener: MediaPlayer.OnErrorListener) {
        mMediaPlayer.setOnErrorListener(listener)
    }

    fun setOnProgressListener(listener: OnProgressListener) {
        mProgressListener = listener
    }

    interface OnProgressListener {
        fun onProgress(progress: Int, position: Int)
    }

}