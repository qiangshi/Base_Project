package com.staff.canteen.api.mvp.view.mine;

import com.staff.canteen.api.mvp.view.IView;

/**
 *
 * @author zenghaiqiang
 * @date 2019/1/25
 */
public interface FeedBackView extends IView{
    /**
     * 反馈成功
     */
    void feedbackSuccess();

    void selectPhoto(int position);

    void onUploadSuccess(String imgUrl,int position);
}
