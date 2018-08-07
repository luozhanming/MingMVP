package com.luozm.mvplibrary;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.ref.WeakReference;

/**
 * Base class of {@link Dialog} using MVP architecture.
 * Created by luozm on 2017/8/30.
 */

public abstract class BaseMVPDialog<T extends IPresenter> extends DialogFragment implements IMVPView {

    protected T mPresenter;
    private WeakReference<BasePresenter> delegate;
    private DialogOptions options;

    /**
     * Create Presenter for this Dialog.
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


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(getLayoutID(), null);
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).setView(view).create();
        bindViews(view);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (options != null) {
            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
            params.width = options.width;
            params.height = options.height;
            params.gravity = options.gravity;
            dialog.setCancelable(options.cancelable);
            dialog.getWindow().setAttributes(params);
        }
        return dialog;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
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
     * Bind view for this activity.
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
    }

    public void setDialogOptions(DialogOptions options) {
        this.options = options;
    }


    public static class DialogOptions {
        int width;
        int height;
        int gravity;
        boolean cancelable = true;
    }


}
