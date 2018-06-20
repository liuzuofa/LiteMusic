package com.lite.litemusic.fragment;

public class FragmentFactory {

    private static FragmentFactory mFragmentFactory;
    private MeFragment mMeFragment;
    private MusicFragment mMusicFragment;
    private DiscoveryFragment mDiscoveryFragment;
    private MusicInfoFragment mMusicInfoFragment;
    private MusicAlbumFragment mMusicAlbumFragment;
    private MusicLyricsFragment mMusicLyricsFragment;

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

    public MusicAlbumFragment getMusicAlbumFragment() {
        if (mMusicAlbumFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mMusicAlbumFragment == null) {
                    mMusicAlbumFragment = new MusicAlbumFragment();
                }
            }
        }
        return mMusicAlbumFragment;
    }

    public MusicLyricsFragment getMusicLyricsFragment() {
        if (mMusicLyricsFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mMusicLyricsFragment == null) {
                    mMusicLyricsFragment = new MusicLyricsFragment();
                }
            }
        }
        return mMusicLyricsFragment;
    }
}
