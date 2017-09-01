package com.luozm.mvplibrary;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by cdc4512 on 2017/8/30.
 */

public abstract class BaseMVPFragment<T extends BasePresenter> extends Fragment implements IMVPView {
    protected T mPresenter;
    private PresenterDelegate delegate;

    protected abstract T createPresenter();


    protected void postCreate(Bundle savedInstanceState) {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutID(),container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter instanceof PresenterDelegate) {
            delegate = (PresenterDelegate) mPresenter;
        } else {
            throw new IllegalArgumentException("Presenter must extends PresenterDelegate");
        }
        preCreate(savedInstanceState);
        delegate.onCreate(savedInstanceState);
        postCreate(savedInstanceState);
    }


    protected void preCreate(Bundle savedInstanceState) {
    }


    protected abstract void bindViews(View view);


    public <T extends View> T findView(int id) {
        return (T) getActivity().findViewById(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        delegate.onDestroy();
        delegate = null;
        mPresenter = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        delegate.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        delegate.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        delegate.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        delegate.onPause();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        delegate.onSave(outState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        delegate.onRestore(savedInstanceState);
    }
}
