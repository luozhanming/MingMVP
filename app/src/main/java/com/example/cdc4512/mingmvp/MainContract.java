package com.example.cdc4512.mingmvp;

import com.luozm.mvplibrary.IPresenter;
import com.luozm.mvplibrary.IView;

/**
 * Created by cdc4512 on 2017/8/30.
 */

public interface MainContract {
    interface View extends IView {
        void operationView1();

    }

    interface Presenter extends IPresenter {
        void operation1();
    }
}
