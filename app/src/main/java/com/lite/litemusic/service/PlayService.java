package com.lite.litemusic.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.lite.litemusic.model.Audio;

import java.io.IOException;
import java.util.List;

public class PlayService extends Service {


    private static final String TAG = "PlayService";
    private PlayServiceBinder mBinder;
    private List<Audio> mAudioList;
    /**
     * 播放器
     */
    private MediaPlayer mPlayer;
    private PrepareListener mPrepareListener;
    private CompletionListener mCompletionListener;

    public PlayService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBinder = new PlayServiceBinder(this);
        initMediaPlayer();
        mPrepareListener = new PrepareListener();
        mCompletionListener = new CompletionListener();
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

    public void prepare(List<Audio> audioList,int position) {
        mAudioList = audioList;
        try {
            mPlayer.reset();
            mPlayer.setDataSource(mAudioList.get(position).getFileUrl());
            mPlayer.prepareAsync();
            mPlayer.setOnPreparedListener(mPrepareListener);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 播放
     */
    public void start() {
        mPlayer.start();
        mPlayer.setOnCompletionListener(mCompletionListener);
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


    public class PlayServiceBinder extends Binder {

        private PlayService mPlayService;

        public PlayServiceBinder(PlayService service) {
            mPlayService = service;
        }

        public PlayService getPlayService() {
            return mPlayService;
        }
    }

    private class PrepareListener implements MediaPlayer.OnPreparedListener {

        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
            start();
        }
    }

    private class CompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {

        }
    }

}
