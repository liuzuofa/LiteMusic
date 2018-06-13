package com.lite.litemusic.fragment;

public class FragmentFactory {

    private static FragmentFactory mFragmentFactory;
    private MeFragment mMeFragment;
    private MusicFragment mMusicFragment;
    private DiscoveryFragment mDiscoveryFragment;
    private PlayFragment mPlayFragment;
    private MusicInfoFragment mMusicInfoFragment;

    public static FragmentFactory newInstance() {
        if (mFragmentFactory == null) {
            synchronized (FragmentFactory.class) {
                if (mFragmentFactory == null) {
                    mFragmentFactory = new FragmentFactory();
                }
            }
        }
        return mFragmentFactory;
    }

    public MeFragment getMeFragment() {
        if (mMeFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mMeFragment == null) {
                    mMeFragment = new MeFragment();
                }
            }
        }
        return mMeFragment;
    }

    public MusicFragment getMusicFragment() {
        if (mMusicFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mMusicFragment == null) {
                    mMusicFragment = new MusicFragment();
                }
            }
        }
        return mMusicFragment;
    }

    public DiscoveryFragment getDiscoveryFragment() {
        if (mDiscoveryFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mDiscoveryFragment == null) {
                    mDiscoveryFragment = new DiscoveryFragment();
                }
            }
        }
        return mDiscoveryFragment;
    }

    public PlayFragment getPlayFragment() {
        if (mPlayFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mPlayFragment == null) {
                    mPlayFragment = new PlayFragment();
                }
            }
        }
        return mPlayFragment;
    }

    public MusicInfoFragment getMusicInfoFragment() {
        if (mMusicInfoFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mMusicInfoFragment == null) {
                    mMusicInfoFragment = new MusicInfoFragment();
                }
            }
        }
        return mMusicInfoFragment;
    }
}
