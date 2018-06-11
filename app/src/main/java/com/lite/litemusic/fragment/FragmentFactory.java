package com.lite.litemusic.fragment;

public class FragmentFactory {

    private static FragmentFactory mFragmentFactory;

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
}
