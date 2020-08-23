package com.jwshi.supermummy.utils.rx;

import android.util.Log;
import android.widget.Toast;

import com.jwshi.supermummy.app.SuperMummyApplication;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by shijiwei on 2020-02-27. @Desc:
 */
public class RxErrorCallAdapter extends CallAdapter.Factory {

    private RxJava2CallAdapterFactory origin;

    private RxErrorCallAdapter() {
        origin = RxJava2CallAdapterFactory.create();
    }

    public static RxErrorCallAdapter create() {
        return new RxErrorCallAdapter();
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new RxCallAdapterWrapper(origin.get(returnType, annotations, retrofit), retrofit);
    }

    class RxCallAdapterWrapper<R> implements CallAdapter<R, Object> {

        private final CallAdapter<R, Object> origin;
        private final Retrofit retrofit;

        public RxCallAdapterWrapper(CallAdapter<R, Object> origin, Retrofit retrofit) {
            this.origin = origin;
            this.retrofit = retrofit;
        }

        @Override
        public Type responseType() {
            return origin.responseType();
        }

        @Override
        public Object adapt(Call<R> call) {
            return ((Observable) origin.adapt(call))
                    .onErrorResumeNext(
                            new Function<Throwable, Throwable>() {
                                @Override
                                public Throwable apply(Throwable throwable) throws Exception {
                                    return handleError(throwable);
                                }
                            });
        }
    }

    private Throwable handleError(Throwable throwable) {

        Log.e("===", throwable.toString());
        Toast.makeText(SuperMummyApplication.getInstance(), throwable.toString(), Toast.LENGTH_LONG).show();
        return throwable;
    }
}
