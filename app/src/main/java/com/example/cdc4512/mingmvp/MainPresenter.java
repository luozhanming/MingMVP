package com.example.cdc4512.mingmvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.luozm.mvplibrary.PresenterDelegate;

/**
 * Created by cdc4512 on 2017/8/30.
 */

public class MainPresenter extends PresenterDelegate<MainContract.View> implements MainContract.Presenter {
    public MainPresenter(Context ctx, MainContract.View v) {
        super(ctx, v);
    }

    @Override
    public void operation1() {
        mView.operationView1();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

    }
}
