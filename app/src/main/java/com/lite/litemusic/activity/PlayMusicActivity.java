package com.lite.litemusic.activity;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.lite.litemusic.R;
import com.lite.litemusic.adapter.ViewPagerAdapter;
import com.lite.litemusic.base.BaseActivity;
import com.lite.litemusic.fragment.FragmentFactory;
import com.lite.litemusic.fragment.MusicAlbumFragment;
import com.lite.litemusic.fragment.MusicInfoFragment;
import com.lite.litemusic.fragment.MusicLyricsFragment;
import com.lite.litemusic.service.PlayService;

import java.util.ArrayList;
import java.util.List;

public class PlayMusicActivity extends BaseActivity {

    private ViewPager mViewPager;
    private MusicInfoFragment mMusicInfoFragment;
    private MusicAlbumFragment mMusicAlbumFragment;
    private MusicLyricsFragment mMusicLyricsFragment;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private PlayService mPlayService;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mPlayService =(PlayService)
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    public int getContentView() {
        return R.layout.activity_play_music;
    }

    @Override
    protected void initView() {
        initFragment();
        mViewPager = (ViewPager) findViewById(R.id.activity_play_view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(adapter);
    }

    private void initFragment() {
        mMusicInfoFragment = FragmentFactory.newInstance().getMusicInfoFragment();
        mMusicAlbumFragment = FragmentFactory.newInstance().getMusicAlbumFragment();
        mMusicLyricsFragment = FragmentFactory.newInstance().getMusicLyricsFragment();
        mFragmentList.add(mMusicInfoFragment);
        mFragmentList.add(mMusicAlbumFragment);
        mFragmentList.add(mMusicLyricsFragment);
    }


}
