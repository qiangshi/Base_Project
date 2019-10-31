package com.staff.canteen.api.mvp.presenter.basepresenter;


import com.staff.canteen.api.mvp.view.IView;

/**
 *
 * @author zenghaiqiang
 * @date 2018/4/23
 * 描述：
 */
public interface IPresenter<V extends IView> {
    void detachView();
}
