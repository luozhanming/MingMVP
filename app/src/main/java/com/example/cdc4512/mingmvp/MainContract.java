package com.example.cdc4512.mingmvp;

import com.luozm.mvplibrary.BasePresenter;
import com.luozm.mvplibrary.BaseView;

/**
 * Created by cdc4512 on 2017/8/30.
 */

public interface MainContract {
    interface View extends BaseView{
        void operationView1();

    }

    interface Presenter extends BasePresenter{
        void operation1();
    }
}
