package com.staff.canteen.api.mvp.presenter.mine;

import com.staff.canteen.api.R;
import com.staff.canteen.api.mvp.presenter.basepresenter.BasePresenter;
import com.staff.canteen.api.ui.activity.BaseActivity;
/**
 *
 * @author zenghaiqiang
 * @date 2019/1/25
 * 设置页面
 */
public class SettingActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {
        initTitle(getResources().getString(R.string.set));
    }
}
