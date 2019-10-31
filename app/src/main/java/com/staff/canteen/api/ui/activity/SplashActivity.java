package com.staff.canteen.api.ui.activity;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.staff.canteen.api.R;
import com.staff.canteen.api.constants.Constant;
import com.staff.canteen.api.mvp.presenter.basepresenter.BasePresenter;
import com.staff.canteen.api.router.RouterCons;
import com.staff.canteen.api.utils.SharePreferenceUtil;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import java.lang.ref.WeakReference;

import androidx.annotation.Nullable;

/**
 *
 * @author pantianxiong
 * @date 2018/5/8
 * 描述：启动页
 */
public class SplashActivity extends BaseActivity {

    private WeakHandler mHandler;

    private static class WeakHandler extends Handler {

        private WeakReference<SplashActivity> mReference;

        private WeakHandler(SplashActivity activity) {
            mReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mReference.get().toMAin();
                    break;
                case 2:
                    mReference.get().toLogin();
                    break;
                default:
                    break;
            }
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {
        mHandler = new WeakHandler(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.TranslucentTheme);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String[] permissions = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (!hasCheckPermission) {
            checkPermission(permissions, getResources().getString(R.string.please_open_read_write_permission), new OnPermissionCheckSuccess() {
                @Override
                public void checkSuccess() {
                    initSplashView();
                }

                @Override
                public void checkFailed() {
                    finish();
                }
            });
        }
    }

    private void initSplashView() {
        boolean isLogin = SharePreferenceUtil.getBoolean(Constant.IS_LOGIN, false);
        if (isLogin) {
            mHandler.sendEmptyMessageDelayed(1, 1500);
        } else {
            mHandler.sendEmptyMessageDelayed(2, 1500);
        }
    }

    /**
     * 跳转到 MainActivity
     */
    private void toMAin() {
        new DefaultUriRequest(this, RouterCons.CREATE_MAIN)
                .start();
        finish();
    }

    private void toLogin() {
        new DefaultUriRequest(this, RouterCons.CREATE_LOGIN)
                .start();
        finish();
    }


    @Override
    protected void onDestroy() {
        if (mHandler != null) {
            mHandler.removeMessages(1);
            mHandler.removeMessages(2);
        }
        super.onDestroy();
    }
}
