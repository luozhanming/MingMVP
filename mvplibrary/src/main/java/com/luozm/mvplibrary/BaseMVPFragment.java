package com.luozm.mvplibrary;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

/**
 * Base class of {@link android.app.Fragment} using MVP architecture.
 * Created by luozm  on 2017/8/30.
 */

public abstract class BaseMVPFragment<T extends IPresenter> extends Fragment implements IMVPView {
    protected T mPresenter;
    private WeakReference<BasePresenter> delegate;

    /**
     * Create Presenter for this Activity.
     */
    protected abstract T createPresenter();

    protected T getPresenter() {
        return mPresenter;
    }

    /**
     * You can do something after Presenter Created in this method.
     */
    protected void postCreate(Bundle savedInstanceState) {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutID(), null);
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
        if (mPresenter instanceof BasePresenter) {
            delegate = new WeakReference<BasePresenter>((BasePresenter) mPresenter);
        } else {
            throw new IllegalArgumentException("Presenter must extends BasePresenter");
        }
        preCreate(savedInstanceState);
        delegate.get().onCreate(savedInstanceState);
        postCreate(savedInstanceState);
    }

    /**
     * You can do something before Presenter Created in this method.
     */
    protected void preCreate(Bundle savedInstanceState) {
    }

    /**
     * Bind view for this fragment.
     */
    protected abstract void bindViews(View view);


    public <T extends View> T findView(int id) {
        return (T) getActivity().findViewById(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        delegate.get().onDestroy();
        mPresenter = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        delegate.get().onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        delegate.get().onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        delegate.get().onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        delegate.get().onPause();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        delegate.get().onSave(outState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        delegate.get().onRestore(savedInstanceState);
    }
}
