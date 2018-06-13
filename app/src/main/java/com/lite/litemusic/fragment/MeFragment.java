package com.lite.litemusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lite.litemusic.R;
import com.lite.litemusic.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MeFragment extends Fragment {

    private ViewPager mViewPager;
    private View mView;
    private PlayFragment mPlayFragment;
    private MusicInfoFragment mMusicInfoFragment;
    private List<Fragment> mFragmentList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_me, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initFragment();
        initView();
    }

    private void initFragment() {
        mPlayFragment = FragmentFactory.newInstance().getPlayFragment();
        mMusicInfoFragment = FragmentFactory.newInstance().getMusicInfoFragment();
        mFragmentList = new ArrayList<>();
        mFragmentList.add(mPlayFragment);
        mFragmentList.add(mMusicInfoFragment);
    }

    private void initView() {
        mViewPager = (ViewPager) mView.findViewById(R.id.fragment_me_view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), mFragmentList);
        mViewPager.setAdapter(adapter);
    }
}
