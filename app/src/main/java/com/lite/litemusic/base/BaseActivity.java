package com.lite.litemusic.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.lite.litemusic.R;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    protected T mPresenter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        initToolBar();
        initView();
    }

    protected abstract void initView();

    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    public abstract int getContentView();
}
