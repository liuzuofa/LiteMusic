package com.lite.litemusic.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;

import com.lite.litemusic.R;
import com.lite.litemusic.adapter.ListViewAdapter;
import com.lite.litemusic.model.Audio;
import com.lite.litemusic.service.PlayService;

import java.util.ArrayList;
import java.util.List;

public class MusicFragment extends Fragment implements ListViewAdapter.Listener{

    private final static String TAG = MusicFragment.class.getSimpleName();
    private ListView mListView;
    private List<Audio> mAudioList;
    private View mView;
    private PlayService mPlayService;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mPlayService = ((PlayService.PlayServiceBinder)iBinder).getPlayService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_music, container, false);
        initView();
        initData();
        return mView;
    }

    private void initView() {
        mListView = (ListView) mView.findViewById(R.id.lv_fragment_music);

    }
    private void initData() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), PlayService.class);
        getContext().bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Cursor cursor = getContext().getContentResolver()
                        .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                                new String[] {MediaStore.Audio.Media._ID,
                                        MediaStore.Audio.Media.DISPLAY_NAME,
                                        MediaStore.Audio.Media.TITLE,
                                        MediaStore.Audio.Media.DURATION,
                                        MediaStore.Audio.Media.ARTIST,
                                        MediaStore.Audio.Media.ALBUM,
                                        MediaStore.Audio.Media.YEAR,
                                        MediaStore.Audio.Media.MIME_TYPE,
                                        MediaStore.Audio.Media.SIZE,
                                        MediaStore.Audio.Media.DATA},
                                null,null,null);
                mAudioList = new ArrayList<>();
                if (cursor.moveToFirst()) {
                    Audio audio = null;
                    do {
                        audio = new Audio();
                        // 文件名
                        audio.setFileName(cursor.getString(1));
                        // 歌曲名
                        audio.setTitle(cursor.getString(2));
                        // 时长
                        audio.setDuration(cursor.getInt(3));
                        // 歌手名
                        audio.setSinger(cursor.getString(4));
                        // 专辑名
                        audio.setAlbum(cursor.getString(5));
                        // 年代
                        if (cursor.getString(6) != null) {
                            audio.setYear(cursor.getString(6));
                        } else {
                            audio.setYear("未知");
                        }
                        // 歌曲格式
                        if ("audio/mpeg".equals(cursor.getString(7).trim())) {
                            audio.setType("mp3");
                        } else if ("audio/x-ms-wma".equals(cursor.getString(7).trim())) {
                            audio.setType("wma");
                        }
                        // 文件大小
                        if (cursor.getString(8) != null) {
                            float size = cursor.getInt(8) / 1024f / 1024f;
                            audio.setSize((size + "").substring(0, 4) + "M");
                        } else {
                            audio.setSize("未知");
                        }
                        // 文件路径
                        if (cursor.getString(9) != null) {
                            audio.setFileUrl(cursor.getString(9));
                        }
                        Log.d(TAG, "audio: " + audio.toString());
                        mAudioList.add(audio);
                    } while (cursor.moveToNext());
                    cursor.close();
                    ListViewAdapter adapter = new ListViewAdapter(getContext(),mAudioList,MusicFragment.this);
                    mListView.setAdapter(adapter);
                }
            }
        }).start();
    }

    @Override
    public void OnMusicItemClick(int position) {
        mPlayService.
    }
}
