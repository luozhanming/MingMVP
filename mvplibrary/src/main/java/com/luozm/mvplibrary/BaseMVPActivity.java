package com.luozm.mvplibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Base class of {@link android.app.Activity} using MVP architecture.
 * Created by luozm on 2017/7/19.
 */

public abstract class BaseMVPActivity<T extends IPresenter> extends AppCompatActivity implements IMVPView {

    protected T mPresenter;
    private WeakReference<BasePresenter> delegate;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        mPresenter = createPresenter();
        if (mPresenter instanceof BasePresenter) {
            delegate = new WeakReference<BasePresenter>((BasePresenter) mPresenter);
        } else {
            throw new IllegalArgumentException("Presenter must extends BasePresenter");
        }
        bindViews();
        preCreate(savedInstanceState);
        delegate.get().onCreate(savedInstanceState);
        postCreate(savedInstanceState);
    }

    /**
     * Create Presenter for this Activity.
     */
    protected abstract T createPresenter();

    protected T getPresenter(){
        return mPresenter;
    }

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
        delegate.get().onDestroy();
        mPresenter = null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        delegate.get().onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        delegate.get().onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        delegate.get().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        delegate.get().onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        delegate.get().onRestart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        delegate.get().onSave(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        delegate.get().onRestore(savedInstanceState);
    }
}
