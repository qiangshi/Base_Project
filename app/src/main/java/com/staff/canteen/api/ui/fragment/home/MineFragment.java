package com.staff.canteen.api.ui.fragment.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.staff.canteen.api.R;
import com.staff.canteen.api.bean.LoginBean;
import com.staff.canteen.api.manager.DataCacheManager;
import com.staff.canteen.api.mvp.presenter.basepresenter.IPresenter;
import com.staff.canteen.api.observer.SynchronizationObserver;
import com.staff.canteen.api.router.RouterCons;
import com.staff.canteen.api.ui.fragment.BaseFragment;
import com.staff.canteen.api.utils.GlidUtils;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 * @author zenghaiqiang
 * @date 2019/1/15
 * 我的Fragment
 */
public class MineFragment extends BaseFragment {

    @BindView(R.id.img_user_head)
    ImageView imgUserHead;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;

    private LoginBean.UserBean userBean;

    @Override
    protected void initEventAndData() {
        SynchronizationObserver.getInstance().registerSynchronizationListener(syncListener, SynchronizationObserver.PAGE_FRAGMENT_TYPE_MINE);
        userBean = DataCacheManager.getUserInfo();
        if (userBean != null) {
            GlidUtils.setCircleGrid(getActivity(), userBean.getPortrait(), imgUserHead);
            tvUserName.setText(userBean.getNickname());
        }
    }


    private SynchronizationObserver.OnSynchronizationListener syncListener = new SynchronizationObserver.OnSynchronizationListener() {
        @Override
        public void onSynchronizationUpdate(int type, Object object) {
            LoginBean.UserBean bean = (LoginBean.UserBean) object;
            if (bean != null) {
                tvUserName.setText(bean.getNickname());
                GlidUtils.setCircleGrid(getActivity(), bean.getPortrait(), imgUserHead);
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {

    }

    @OnClick({R.id.rel_user_info, R.id.lin_time_bean, R.id.lin_my_company, R.id.lin_feed_back, R.id.lin_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //修改用户信息
            case R.id.rel_user_info:
                new DefaultUriRequest(Objects.requireNonNull(getActivity()), RouterCons.MODIFY_USER_INFO)
                        .start();
                break;
            //时间豆界面
            case R.id.lin_time_bean:
                break;
            //企业管理
            case R.id.lin_my_company:
                break;
            //问题与反馈
            case R.id.lin_feed_back:
                new DefaultUriRequest(Objects.requireNonNull(getActivity()), RouterCons.CREATE_QUESTION_FEEDBACK)
                        .start();
                break;
            //设置
            case R.id.lin_set:
                new DefaultUriRequest(Objects.requireNonNull(getActivity()), RouterCons.CREATE_SETTING)
                        .start();
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        SynchronizationObserver.getInstance().unRegisterSynchronizationListener(SynchronizationObserver.PAGE_FRAGMENT_TYPE_MINE);
        super.onDestroy();
    }
}
