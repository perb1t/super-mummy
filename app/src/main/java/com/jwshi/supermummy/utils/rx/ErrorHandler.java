package com.jwshi.supermummy.utils.rx;

import android.util.Log;

import io.reactivex.functions.Consumer;

/**
 * Created by shijiwei on 2020-02-27.
 *
 * @Desc:
 */
public class ErrorHandler implements Consumer<Throwable> {


    @Override
    public void accept(Throwable throwable) throws Exception {

        Log.e("=ErrorHandler==",throwable.getMessage());
    }
}
