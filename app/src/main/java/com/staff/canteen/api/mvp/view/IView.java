package com.staff.canteen.api.mvp.view;

/**
 *
 * @author zenghaiqiang
 * @date 2018/4/23
 * 描述：
 */
public interface IView {
    /**
     * 显示Loading
     */
    void showLoading();

    /**
     * 隐藏Loading
     */
    void hideLoading();

    /**
     * 吐司
     *
     * @param stringId 吐司的字符
     */
    void toastMessage(int stringId);
}
