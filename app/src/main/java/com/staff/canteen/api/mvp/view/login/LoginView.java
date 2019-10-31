package com.staff.canteen.api.mvp.view.login;

import com.staff.canteen.api.bean.LoginBean;
import com.staff.canteen.api.mvp.view.IView;

/**
 *
 * @author zenghaiqiang
 * @date 2019/1/16
 */
public interface LoginView extends IView {

    /**
     * 验证码倒计时
     */
    void changeBtnStatus();

    /**
     * 登录成功
     * @param loginBean
     */
    void loginSuccess(LoginBean loginBean);

}
