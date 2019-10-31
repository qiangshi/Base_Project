package com.staff.canteen.api.ui.fragment.home;

import com.staff.canteen.api.R;
import com.staff.canteen.api.mvp.presenter.home.MacroscopicPresenter;
import com.staff.canteen.api.mvp.view.home.MacroscopicView;
import com.staff.canteen.api.ui.fragment.BaseFragment;

/**
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/3/1 10:22
 */
public class BuildFragment extends BaseFragment<MacroscopicPresenter> implements MacroscopicView {


    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_macroscopic;
    }

    @Override
    protected MacroscopicPresenter getPresenter() {
        return null;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {

    }
}
