package com.staff.canteen.api.ui.fragment.home;


import android.widget.ImageView;
import android.widget.TextView;
import com.staff.canteen.api.R;
import com.staff.canteen.api.mvp.presenter.home.SelfSelectPresenter;
import com.staff.canteen.api.mvp.view.home.SelfSelectView;
import com.staff.canteen.api.ui.fragment.BaseFragment;
import com.staff.canteen.api.utils.MLog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 *
 * @author zenghaiqiang
 * @date 2019/1/15
 */
public class OrderSearchFragment extends BaseFragment<SelfSelectPresenter> implements SelfSelectView, OnRefreshListener {

    @BindView(R.id.rv_message)
    RecyclerView rvMessage;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;


    @Override
    protected void initEventAndData() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_self_select;
    }

    @Override
    protected SelfSelectPresenter getPresenter() {
        return new SelfSelectPresenter(this);
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        MLog.d("onFragmentVisibleChange");
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh(2000);
    }


}
