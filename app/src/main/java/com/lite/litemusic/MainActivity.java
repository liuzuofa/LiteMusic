package com.lite.litemusic;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.lite.litemusic.adapter.ViewPagerAdapter;
import com.lite.litemusic.base.BaseActivity;
import com.lite.litemusic.fragment.DiscoveryFragment;
import com.lite.litemusic.fragment.FragmentFactory;
import com.lite.litemusic.fragment.MeFragment;
import com.lite.litemusic.fragment.MusicFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private final static String TAG = "MainActivity";

    private DrawerLayout mDrawerLayout;
    private TextView mMeTextView;
    private TextView mMusicTextView;
    private TextView mDiscoveryTextView;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;

    private MeFragment mMeFragment;
    private MusicFragment mMusicFragment;
    private DiscoveryFragment mDiscoveryFragment;
    private List<Fragment> mFragmentList;
    private final int FRAGMENT_ME_POSITION = 0;
    private final int FRAGMENT_MUSIC_POSITION = 1;
    private final int FRAGMENT_DISCOVERY_POSITION = 2;

    @Override
    protected void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu);
        }
        mMeTextView = (TextView) findViewById(R.id.tv_me);
        mMeTextView.setOnClickListener(this);
        mMusicTextView = (TextView) findViewById(R.id.tv_music);
        mMusicTextView.setOnClickListener(this);
        mDiscoveryTextView = (TextView) findViewById(R.id.tv_discovery);
        mDiscoveryTextView.setOnClickListener(this);
        initFragment();
        initViewPager();

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
        Log.d(TAG, "onOptionsItemSelected: ");
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
                mViewPager.setCurrentItem(FRAGMENT_ME_POSITION);
                break;
            case R.id.tv_music:
                mMusicTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.toolbar_select_text_size));
                mViewPager.setCurrentItem(FRAGMENT_MUSIC_POSITION);
                break;
            case R.id.tv_discovery:
                mDiscoveryTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.toolbar_select_text_size));
                mViewPager.setCurrentItem(FRAGMENT_DISCOVERY_POSITION);
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
}
