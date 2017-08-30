package com.luozm.mvplibrary;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by cdc4512 on 2017/7/19.
 */

public abstract class BaseMVPActivity<T extends BasePresenter> extends AppCompatActivity implements IMVPView {

    protected T mPresenter;
    private PresenterDelegate delegate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        mPresenter = createPresenter();
        if (mPresenter instanceof PresenterDelegate) {
            delegate = (PresenterDelegate) mPresenter;
        } else {
            throw new IllegalArgumentException("Presenter must extends PresenterDelegate");
        }
        bindViews();
        preCreate(savedInstanceState);
        delegate.onCreate(savedInstanceState);
        postCreate(savedInstanceState);
    }

    protected abstract T createPresenter();

    /**
     * Presenter onCreate方法前执行
     */
    protected void postCreate(Bundle savedInstanceState) {
    }

    /**
     * Presenter onCreate方法后执行
     */
    protected void preCreate(Bundle savedInstanceState) {
    }

    /**
     * 绑定界面控件
     */
    protected abstract void bindViews();


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
