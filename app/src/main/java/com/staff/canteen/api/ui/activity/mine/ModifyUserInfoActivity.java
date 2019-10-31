package com.staff.canteen.api.ui.activity.mine;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.staff.canteen.api.R;
import com.staff.canteen.api.bean.DataEvent;
import com.staff.canteen.api.bean.LoginBean;
import com.staff.canteen.api.constants.Constant;
import com.staff.canteen.api.helper.ToolHelper;
import com.staff.canteen.api.manager.DataCacheManager;
import com.staff.canteen.api.mvp.presenter.mine.ModifyUserInfoPresenter;
import com.staff.canteen.api.mvp.view.mine.ModifyUserInfoView;
import com.staff.canteen.api.observer.SynchronizationObserver;
import com.staff.canteen.api.router.RouterCons;
import com.staff.canteen.api.ui.activity.BaseActivity;
import com.staff.canteen.api.ui.fragment.dialog.TypeFilterFragment;
import com.staff.canteen.api.utils.DataCheckUtils;
import com.staff.canteen.api.utils.GlidUtils;
import com.staff.canteen.api.utils.SelectImageUtils;
import com.staff.canteen.api.utils.StringUtils;
import com.sankuai.waimai.router.annotation.RouterUri;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 *
 * @author zenghaiqiang
 * @date 2019/01/24
 * 描述：修改用户信息
 */
@RouterUri(path = {RouterCons.MODIFY_USER_INFO})
public class ModifyUserInfoActivity extends BaseActivity<ModifyUserInfoPresenter> implements ModifyUserInfoView {

    /**用户头像*/
    @BindView(R.id.img_my_head)
    ImageView imgMyHead;
    /**用户昵称*/
    @BindView(R.id.tv_nick_name)
    TextView tvNickName;
    /**手机号*/
    @BindView(R.id.tv_phone_code)
    TextView tvPhoneCode;
    /**性别*/
    @BindView(R.id.tv_gender)
    TextView tvGender;
    /**出生日期*/
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    /**时间豆*/
    @BindView(R.id.tv_time_bean)
    TextView tvTimeBean;
    /**图片资源*/
    private String logoUrl;

    private LoginBean.UserBean userBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_user_info;
    }

    @Override
    protected ModifyUserInfoPresenter getPresenter() {
        return new ModifyUserInfoPresenter(this);
    }

    @Override
    protected void initDataAndEvent() {
        initRightTitle(getResources().getString(R.string.my_info), getResources().getString(R.string.preservation));
        userBean = DataCacheManager.getUserInfo();
        mPresenter.getUserInfo();
    }


    private int genderPos = 2;

    @OnClick({R.id.tv_right_btn, R.id.lin_my_head, R.id.lin_nick_name, R.id.lin_phone_code, R.id.lin_gender, R.id.lin_birthday})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //保存
            case R.id.tv_right_btn:
                MultipartBody.Part portrait = null;
                if (StringUtils.isNotEmpty(logoUrl)) {
                    File file = new File(logoUrl);
                    RequestBody fileRQ = RequestBody.create(MediaType.parse("image/*"), file);
                    portrait = MultipartBody.Part.createFormData("portrait", file.getName(), fileRQ);
                }
                MultipartBody.Part userId = MultipartBody.Part.createFormData("user_id", String.valueOf(DataCacheManager.getUserInfo().getId()));
                MultipartBody.Part token = MultipartBody.Part.createFormData("token", DataCacheManager.getToken());
                MultipartBody.Part nickName = MultipartBody.Part.createFormData("nickname", tvNickName.getText().toString());
                MultipartBody.Part contact = MultipartBody.Part.createFormData("contact", tvPhoneCode.getText().toString());
                MultipartBody.Part gender = MultipartBody.Part.createFormData("gender", String.valueOf(ModifyUserInfoActivity.this.genderPos));
                MultipartBody.Part birthday = MultipartBody.Part.createFormData("birthday", tvBirthday.getText().toString());
                mPresenter.updateUserInfo(userId, token, portrait, nickName, contact, gender, birthday);
                break;
            //点击修改头像
            case R.id.lin_my_head:
                mPresenter.autoObtainStoragePermission(this);
                break;
            //昵称
            case R.id.lin_nick_name:
                new DefaultUriRequest(this, RouterCons.CREATE_CPY_SET_INFO)
                        .putExtra(RouterCons.TYPE, 5)
                        .activityRequestCode(Constant.REQUEST_CODE_USER_NICK_NAME)
                        .start();
                break;
            //修改手机号
            case R.id.lin_phone_code:
                new DefaultUriRequest(this, RouterCons.CREATE_CPY_SET_INFO)
                        .putExtra(RouterCons.TYPE, 6)
                        .activityRequestCode(Constant.REQUEST_CODE_PHONE)
                        .start();
                break;
            //性别
            case R.id.lin_gender:
                TypeFilterFragment.showFragment(getSupportFragmentManager(), Arrays.asList(getResources().getStringArray(R.array.gender_sex)), genderPos,
                        pos -> {
                            ModifyUserInfoActivity.this.genderPos = pos;
                            tvGender.setText(Arrays.asList(getResources().getStringArray(R.array.gender_sex)).get(pos));
                        });
                break;
            //出生日期
            case R.id.lin_birthday:
                ToolHelper.selectTime(this, tvBirthday, Constant.DATE_FORMAT_0);
                break;
            default:
                break;
        }
    }

    @Override
    public void getUserInfoSuccess(LoginBean.UserBean bean) {
        userBean.setPhone(bean.getPhone());
        userBean.setNickname(bean.getNickname());
        userBean.setPortrait(bean.getPortrait());
        userBean.setGender(bean.getGender());
        userBean.setContact(bean.getContact());
        userBean.setBirthday(bean.getBirthday());
        userBean.setCoins(bean.getCoins());
        DataCacheManager.saveUserInfo(userBean);
        EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_CHANGE_USERINFO, userBean));
        SynchronizationObserver.getInstance().onSynchronizationUpdate(SynchronizationObserver.TYPE_UPDATE_USER_INFO, userBean, SynchronizationObserver.PAGE_FRAGMENT_TYPE_MINE);
        if (!TextUtils.isEmpty(bean.getPortrait())) {
            imgMyHead.setVisibility(View.VISIBLE);
            GlidUtils.setCircleGrid(this, bean.getPortrait(), imgMyHead);
        } else {
            imgMyHead.setVisibility(View.GONE);
        }
        tvNickName.setText(bean.getNickname());
        tvPhoneCode.setText(bean.getContact());
        genderPos = Integer.valueOf(bean.getGender());
        if ("0".equals(bean.getGender())) {
            tvGender.setText("男");
        } else if ("1".equals(bean.getGender())) {
            tvGender.setText("女");
        } else {
            tvGender.setText("未知");
        }
        tvBirthday.setText(bean.getBirthday());
        tvTimeBean.setText(bean.getCoins());
    }

    @Override
    public void updateUserInfoSuccess() {
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(this, requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //用户昵称
                case Constant.REQUEST_CODE_USER_NICK_NAME:
                    String reason = data.getStringExtra(RouterCons.DATA);
                    tvNickName.setText(reason);
                    break;
                //用户手机号
                case Constant.REQUEST_CODE_PHONE:
                    String mobilePhone = data.getStringExtra(RouterCons.DATA);
                    if (!DataCheckUtils.checkPhone(mobilePhone)) {
                        toastMessage(R.string.msg_phone_num_error);
                        break;
                    }
                    tvPhoneCode.setText(mobilePhone);
                    break;
                default:
                    break;
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPresenter.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void selectPhoto() {
        SelectImageUtils.selectPhoto(this, getString(R.string.takephoto), false, true, 1);
    }

    @Override
    public void onUploadSuccess(String imgUrl) {
        imgMyHead.setVisibility(View.VISIBLE);
        Glide.with(this).load(imgUrl).into(imgMyHead);
        logoUrl = imgUrl;
    }
}
