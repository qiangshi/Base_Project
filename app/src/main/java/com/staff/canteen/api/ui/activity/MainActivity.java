package com.staff.canteen.api.ui.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import com.staff.canteen.api.R;
import com.staff.canteen.api.bean.DataEvent;
import com.staff.canteen.api.mvp.presenter.basepresenter.BasePresenter;
import com.staff.canteen.api.router.RouterCons;
import com.staff.canteen.api.ui.fragment.home.HomeFragment;
import com.staff.canteen.api.ui.fragment.home.BuildFragment;
import com.staff.canteen.api.ui.fragment.home.OrderSearchFragment;
import com.staff.canteen.api.ui.fragment.home.MineFragment;
import com.staff.canteen.api.view.MainBottomView;
import com.sankuai.waimai.router.annotation.RouterUri;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import static com.staff.canteen.api.bean.DataEvent.TYPE_CHANGE_INFO_DOT;

/**
 * @author zenghaiqiang
 * @date 2019/10/31
 * 描述：主界面
 */
@RouterUri(path = {RouterCons.CREATE_MAIN})
public class MainActivity extends BaseActivity implements MainBottomView.HomeBottomClick {

    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.mbv_home)
    MainBottomView mbvHome;

    private HomeFragment homeFragment;
    private OrderSearchFragment orderSearchFragment;
    private BuildFragment buildFragment;
    private MineFragment mineFragment;

    private FragmentTransaction fragmentTransaction;

    private boolean isExit = false;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {
        homeClick();
        mbvHome.setHomeBottomClick(this);
    }

    private void hide(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (orderSearchFragment != null) {
            transaction.hide(orderSearchFragment);
        }
        if (buildFragment != null) {
            transaction.hide(buildFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }


    @Override
    public void homeClick() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hide(fragmentTransaction);
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            fragmentTransaction.add(R.id.frame_layout, homeFragment);
        } else {
            fragmentTransaction.show(homeFragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void orderSearchClick() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hide(fragmentTransaction);
        if (orderSearchFragment == null) {
            orderSearchFragment = new OrderSearchFragment();
            fragmentTransaction.add(R.id.frame_layout, orderSearchFragment);
        } else {
            fragmentTransaction.show(orderSearchFragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void buildClick() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hide(fragmentTransaction);
        if (buildFragment == null) {
            buildFragment = new BuildFragment();
            fragmentTransaction.add(R.id.frame_layout, buildFragment);
        } else {
            fragmentTransaction.show(buildFragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void mineClick() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hide(fragmentTransaction);
        if (mineFragment == null) {
            mineFragment = new MineFragment();
            fragmentTransaction.add(R.id.frame_layout, mineFragment);
        } else {
            fragmentTransaction.show(mineFragment);
        }
        fragmentTransaction.commit();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataEvent(DataEvent event) {
        if (event.getType() == TYPE_CHANGE_INFO_DOT) {
            Boolean isVisible = (Boolean) event.getData();
            mbvHome.showSelfDot(isVisible);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            toastMessage(R.string.again_pass_logout_app);
            //利用handler延迟发送更改状态信息
            handler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }
}
