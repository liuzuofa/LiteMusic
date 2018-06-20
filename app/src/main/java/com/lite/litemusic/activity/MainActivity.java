package com.lite.litemusic.activity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lite.litemusic.R;
import com.lite.litemusic.adapter.ViewPagerAdapter;
import com.lite.litemusic.base.BaseActivity;
import com.lite.litemusic.fragment.DiscoveryFragment;
import com.lite.litemusic.fragment.FragmentFactory;
import com.lite.litemusic.fragment.MeFragment;
import com.lite.litemusic.fragment.MusicFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private final static String TAG = "MainActivity";

    private DrawerLayout mDrawerLayout;
    private TextView mMeTextView;
    private TextView mMusicTextView;
    private TextView mDiscoveryTextView;
    private ActionBar mActionBar;

    private MeFragment mMeFragment;
    private MusicFragment mMusicFragment;
    private DiscoveryFragment mDiscoveryFragment;
    private List<Fragment> mFragmentList;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private LinearLayout mLinearLayout;

    private final int FRAGMENT_ME_POSITION = 0;
    private final int FRAGMENT_MUSIC_POSITION = 1;
    private final int FRAGMENT_DISCOVERY_POSITION = 2;
    private final static String[] permissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    private final static int PERMISSION_REQUEST = 1;


    @Override
    protected void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeAsUpIndicator(R.drawable.menu);
        }
        mMeTextView = (TextView) findViewById(R.id.tv_me);
        mMeTextView.setOnClickListener(this);
        mMusicTextView = (TextView) findViewById(R.id.tv_music);
        mMusicTextView.setOnClickListener(this);
        mDiscoveryTextView = (TextView) findViewById(R.id.tv_discovery);
        mDiscoveryTextView.setOnClickListener(this);
        mLinearLayout = (LinearLayout) findViewById(R.id.include_play_ll);
        mLinearLayout.setOnClickListener(this);
        initFragment();
        initViewPager();
        grantPermission();
    }

    private void initFragment() {
        mMeFragment = FragmentFactory.newInstance().getMeFragment();
        mMusicFragment = FragmentFactory.newInstance().getMusicFragment();
        mDiscoveryFragment = FragmentFactory.newInstance().getDiscoveryFragment();
        mFragmentList = new ArrayList<>();
        mFragmentList.add(mMeFragment);
        mFragmentList.add(mMusicFragment);
        mFragmentList.add(mDiscoveryFragment);
    }

    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setCurrentItem(FRAGMENT_MUSIC_POSITION);
        mViewPager.addOnPageChangeListener(this);
    }


    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        mMeTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.toolbar_text_size));
        mMusicTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.toolbar_text_size));
        mDiscoveryTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.toolbar_text_size));
        switch (view.getId()) {
            case R.id.tv_me:
                mMeTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.toolbar_select_text_size));
                break;
            case R.id.tv_music:
                mMusicTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.toolbar_select_text_size));
                break;
            case R.id.tv_discovery:
                mDiscoveryTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.toolbar_select_text_size));
                break;
            case R.id.include_play_ll:
                startActivity(new Intent(this, PlayMusicActivity.class));
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mMeTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.toolbar_text_size));
        mMusicTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.toolbar_text_size));
        mDiscoveryTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.toolbar_text_size));
        if (FRAGMENT_ME_POSITION == position) {
            mMeTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.toolbar_select_text_size));
        } else if (FRAGMENT_MUSIC_POSITION == position) {
            mMusicTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.toolbar_select_text_size));
        } else if (FRAGMENT_DISCOVERY_POSITION == position) {
            mDiscoveryTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.toolbar_select_text_size));
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void grantPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            Log.d(TAG, "grantPermission: " + Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(MainActivity.this, permissions, PERMISSION_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                finish();
            }
        }
    }
}
