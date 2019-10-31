package com.staff.canteen.api.net;

import com.staff.canteen.api.bean.DataEvent;
import com.staff.canteen.api.net.exception.ResponseException;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/3/1 10:24
 */
public abstract class BaseObserver<E> implements Observer<E> {

    private Disposable mDisposable;

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ResponseException) {
            if ("token不匹配".equals(((ResponseException) e).getErrorMessage())) {
                EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_LOGIN, null));
            } else {
                onError(((ResponseException) e));
            }
        } else {
            onError(new ResponseException(e, 1009, e.getMessage()));
        }
    }

    public Disposable getDisposable() {
        return mDisposable;
    }

    public abstract void onError(ResponseException e);

}
