package com.jwshi.supermummy.presentation.base;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RxPresenter<V extends BaseView> implements BasePresenter<V> {

    private V baseView;
    private CompositeDisposable mDisposable;

    public void attach(V baseView) {
        mDisposable = new CompositeDisposable();
        this.baseView = baseView;
    }

    public void detach() {
        baseView = null;
        dispose();
    }

    @Override
    public V getBaseView() {
        return baseView;
    }

    public void addDisposable(Disposable disposable) {
        if (mDisposable != null && disposable != null) {
            mDisposable.add(disposable);
        }
    }

    public void dispose() {
        if (mDisposable != null) {
            mDisposable.dispose();
            mDisposable.clear();
        }
    }

    public <T> Observable<T> subscribeOnMainThread(Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public <T, E extends Observer<? super T> & Disposable> E subscribeWith(Observable<T> observable,
                                                                           E observer) {
        addDisposable(observer);
        return subscribeOnMainThread(observable).subscribeWith(observer);
    }
}
