package com.staff.canteen.api.ui.fragment.home;

import com.staff.canteen.api.R;
import com.staff.canteen.api.mvp.presenter.home.HomePresenter;
import com.staff.canteen.api.mvp.view.home.HomeView;
import com.staff.canteen.api.ui.fragment.BaseFragment;

/**
 * Description: java类作用描述
 * @author: 曾海强
 * CreateDate: 2019/10/31 11:28
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter getPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {

    }

    @Override
    protected void initEventAndData() {

    }
}