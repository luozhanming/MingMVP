package com.luozm.mvplibrary;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by cdc4512 on 2017/8/30.
 */

public abstract class BaseMVPDialog<T extends BasePresenter> extends DialogFragment implements IMVPView {

    protected T mPresenter;
    private PresenterDelegate delegate;
    private DialogOptions options;

    protected abstract T createPresenter();


    /**
     * Presenter onCreate方法前执行
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
        if (mPresenter instanceof PresenterDelegate) {
            delegate = (PresenterDelegate) mPresenter;
        } else {
            throw new IllegalArgumentException("Presenter must extends PresenterDelegate");
        }
        preCreate(savedInstanceState);
        delegate.onCreate(savedInstanceState);
        postCreate(savedInstanceState);
    }

    /**
     * Presenter onCreate方法后执行
     */
    protected void preCreate(Bundle savedInstanceState) {
    }

    /**
     * 绑定界面控件
     *
     * @param view
     */
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
