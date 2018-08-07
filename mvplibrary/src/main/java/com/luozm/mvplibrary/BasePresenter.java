package com.luozm.mvplibrary;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Base class of Presenter.
 * Created by luozm on 2017/7/19.
 */

public abstract class BasePresenter<T extends IView> {
    private Context mContext;
    private T mView;

    public BasePresenter(Context ctx, T v) {
        this.mContext = ctx;
        this.mView = v;
    }

    public abstract void onCreate(@Nullable Bundle savedInstanceState);

    public void onDestroy() {
        mContext = null;
        mView = null;
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public void onRestart(){}

    public void onSave(Bundle savedInstance) {
    }

    public void onRestore(Bundle savedInstance) {
    }

    public T getView(){
        return mView;
    }

    public Context getContext(){
        return mContext;
    }
}
