package com.staff.canteen.api.mvp.view.mine;

import com.staff.canteen.api.bean.LoginBean;
import com.staff.canteen.api.mvp.view.IView;

/**
 *
 * @author zenghaiqiang
 * @date 2019/1/24
 */
public interface ModifyUserInfoView extends IView {
    /**
     * 获取用户信息成功
     * @param bean 返回的登录信息
     */
    void getUserInfoSuccess(LoginBean.UserBean bean);

    /**
     * 更新用户信息成功
     */
    void updateUserInfoSuccess();


    void selectPhoto();

    void onUploadSuccess(String imgUrl);
}
