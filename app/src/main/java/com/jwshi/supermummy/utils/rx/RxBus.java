package com.jwshi.supermummy.utils.rx;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by shijiwei on 2020-02-27.
 *
 * @Desc:
 */
public class RxBus {

    private static volatile RxBus instance;

    private Subject<Object> bus;

    private RxBus(){
        bus = PublishSubject.create().toSerialized();
    }

    public static RxBus getDefault(){
        if (instance == null){
            synchronized (RxBus.class){
                instance = new RxBus();
            }
        }
        return instance;
    }


    public void post(Object o){
        bus.onNext(o);
    }

    public <T> Observable<T> toObservel(Class<T> clz){
        return bus.ofType(clz);
    }
}
