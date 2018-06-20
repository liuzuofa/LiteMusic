package com.lite.litemusic.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

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
     * 播放或者暂停
     */
    public void pause() {
        if (isPlaying()) {

        } else {

        }
    }

    private boolean isPlaying() {
        return mPlayer.isPlaying();
    }

    public void start() {

    }

    public void stop() {

    }

    private static class PlayServiceBinder extends Binder {

        private PlayService mPlayService;

        public PlayServiceBinder(PlayService service) {
            mPlayService = service;
        }

        public PlayService getPlayService() {
            return mPlayService;
        }
    }
}
