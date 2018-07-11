package com.lite.litemusic.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

public class PlayService extends Service {

    private static final String TAG = "PlayService";
    private PlayServiceBinder mBinder;
    /**
     * 播放器
     */
    private MediaPlayer mPlayer;

    public PlayService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBinder = new PlayServiceBinder(this);
        initMediaPlayer();
    }

    private void initMediaPlayer() {
        if (mPlayer == null) {
            mPlayer = new MediaPlayer();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    /**
     * 播放
     */
    public void start() {
        mPlayer.start();
    }

    /**
     * 停止
     */
    public void stop() {
        mPlayer.stop();
    }

    /**
     * 暂停
     */
    public void pause() {
        if (!isPlaying()) {
            return;
        }
        mPlayer.pause();
    }

    /**
     * 继续
     */
    private void resume() {
        if (isPlaying()) {
            return;
        }

    }

    /**
     * 是否正在播放音乐
     *
     * @return true or false
     */
    private boolean isPlaying() {
        return mPlayer.isPlaying();
    }


    class PlayServiceBinder extends Binder {

        private PlayService mPlayService;

        public PlayServiceBinder(PlayService service) {
            mPlayService = service;
        }

        public PlayService getPlayService() {
            return mPlayService;
        }
    }
}
