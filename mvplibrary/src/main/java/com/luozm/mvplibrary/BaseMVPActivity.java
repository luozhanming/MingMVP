package com.luozm.mvplibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by cdc4512 on 2017/7/19.
 */

public abstract class BaseMVPActivity<T extends IPresenter> extends AppCompatActivity implements IMVPView {

    protected T mPresenter;
    private BasePresenter delegate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        mPresenter = createPresenter();
        if (mPresenter instanceof BasePresenter) {
            delegate = (BasePresenter) mPresenter;
        } else {
            throw new IllegalArgumentException("Presenter must extends BasePresenter");
        }
        bindViews();
        preCreate(savedInstanceState);
        delegate.onCreate(savedInstanceState);
        postCreate(savedInstanceState);
    }

    /**
     * Create Presenter for this Activity.
     */
    protected abstract T createPresenter();

    /**
     * You can do something after Presenter Created in this method.
     */
    protected void postCreate(Bundle savedInstanceState) {
    }

    /**
     * You can do something before Presenter Created in this method.
     */
    protected void preCreate(Bundle savedInstanceState) {
    }

    /**
     * Bind view for this activity.
     */
    protected abstract void bindViews();

    /**
     * Simple way to findView without cast the type.
     */
    public <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        delegate.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        delegate.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        delegate.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        delegate.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        delegate.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        delegate.onRestart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        delegate.onSave(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        delegate.onRestore(savedInstanceState);
    }
}
