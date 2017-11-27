package com.hugh.okhttp.listener;

import java.util.Objects;

/**
 * @author  hugh
 * @function 该方法用来进行事件的回调回调网络访问是否能够成功
 */

public interface ResponseListener {
    /**
     * 成功
     * @param object
     */
    void onSuccess(Object object);

    /**
     * 失败
     * @param object
     */
    void onFailer(Object object);
}
