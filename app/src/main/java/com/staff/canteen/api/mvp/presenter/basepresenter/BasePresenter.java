package com.staff.canteen.api.mvp.presenter.basepresenter;


import android.content.Context;

import com.staff.canteen.api.mvp.view.IView;
import com.staff.canteen.api.net.ApiClient;
import com.staff.canteen.api.net.ApiStores;
import com.staff.canteen.api.net.BaseObserver;
import com.staff.canteen.api.net.ResponseConvert;
import com.staff.canteen.api.net.exception.ExceptionConvert;
import com.staff.canteen.api.ui.activity.BaseActivity;
import com.staff.canteen.api.ui.fragment.BaseFragment;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 *
 * @author zenghaiqiang
 * @date 2018/4/23
 * 描述：
 */
public class BasePresenter<V extends IView> implements IPresenter<V> {

    protected V mView;
    protected Context context;
    protected ApiStores apiStores = ApiClient.retrofit().create(ApiStores.class);
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public BasePresenter(V view) {
        mView = view;
        if (view instanceof BaseActivity) {
            context = (BaseActivity<BasePresenter<IView>>) view;
        } else if (view instanceof BaseFragment) {
            context = ((BaseFragment) view).getActivity();
        }
    }

    @Override
    public void detachView() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
        mView = null;
    }

    public IView getView() {
        return mView;
    }

    protected void addSubscription(Observable observable, BaseObserver observer) {
        observable.map(new ResponseConvert())
                .onErrorResumeNext(new ExceptionConvert())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        compositeDisposable.add(observer.getDisposable());
    }

    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    protected void removeSubscription(Disposable disposable) {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.remove(disposable);
        }
    }
}
