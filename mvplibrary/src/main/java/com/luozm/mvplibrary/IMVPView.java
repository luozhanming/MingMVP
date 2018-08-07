package com.luozm.mvplibrary;

import android.support.annotation.LayoutRes;

/**
 * Created by luozm on 2017/7/19.
 */

public interface IMVPView {
    /**
     * Specify layout id for the view content.
     */
    @LayoutRes int getLayoutID();


}
