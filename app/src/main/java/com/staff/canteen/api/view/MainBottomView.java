package com.staff.canteen.api.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.staff.canteen.api.R;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author zenghaiqiang
 * @date 2019/1/11
 * 描述：首页底部按钮
 */
public class MainBottomView extends LinearLayout {

    @BindView(R.id.img_home)
    ImageView imgHome;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.img_red_top_home)
    ImageView imgRedTopHome;
    @BindView(R.id.img_order_search)
    ImageView imgOrderSearch;
    @BindView(R.id.tv_order_search)
    TextView tvOrderSearch;
    @BindView(R.id.img_red_top_order_search)
    ImageView imgRedTopOrderSearch;
    @BindView(R.id.img_mine)
    ImageView imgMine;
    @BindView(R.id.tv_mine)
    TextView tvMine;
    @BindView(R.id.img_red_top_mine)
    ImageView imgRedTopMine;
    @BindView(R.id.img_build)
    ImageView imgBuild;
    @BindView(R.id.tv_build)
    TextView tvBuild;

    private Context context;

    public MainBottomView(Context context) {
        this(context, null);
    }

    public MainBottomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainBottomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_main, this);
        ButterKnife.bind(this, view);
    }

    public void switchBottom(int pos) {
        switch (pos) {
            case 0:
                tvHome.setTextColor(ContextCompat.getColor(context,R.color.color_4C84FF));
                tvOrderSearch.setTextColor(ContextCompat.getColor(context,R.color.color_8D92A3));
                tvMine.setTextColor(ContextCompat.getColor(context,R.color.color_8D92A3));
                tvBuild.setTextColor(ContextCompat.getColor(context,R.color.color_8D92A3));
                imgHome.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_news_selection_iconxhdpi));
                imgOrderSearch.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_news_unselection_iconxhdpi));
                imgMine.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_me_unselection_iconxhdpi));
                imgBuild.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_me_unselection_iconxhdpi));
                if (homeBottomClick != null) {
                    homeBottomClick.homeClick();
                }
                break;
            case 1:
                tvOrderSearch.setTextColor(ContextCompat.getColor(context,R.color.color_4C84FF));
                tvHome.setTextColor(ContextCompat.getColor(context,R.color.color_8D92A3));
                tvMine.setTextColor(ContextCompat.getColor(context,R.color.color_8D92A3));
                tvBuild.setTextColor(ContextCompat.getColor(context,R.color.color_8D92A3));
                imgOrderSearch.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_news_selection_iconxhdpi));
                imgHome.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_news_unselection_iconxhdpi));
                imgMine.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_me_unselection_iconxhdpi));
                imgBuild.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_me_unselection_iconxhdpi));
                if (homeBottomClick != null) {
                    homeBottomClick.orderSearchClick();
                }
                break;
            case 2:
                tvOrderSearch.setTextColor(ContextCompat.getColor(context,R.color.color_8D92A3));
                tvHome.setTextColor(ContextCompat.getColor(context,R.color.color_8D92A3));
                tvMine.setTextColor(ContextCompat.getColor(context,R.color.color_8D92A3));
                tvBuild.setTextColor(ContextCompat.getColor(context,R.color.color_4C84FF));
                imgOrderSearch.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_news_unselection_iconxhdpi));
                imgHome.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_news_unselection_iconxhdpi));
                imgMine.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_me_unselection_iconxhdpi));
                imgBuild.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_me_selection_iconxhdpi));
                if (homeBottomClick != null) {
                    homeBottomClick.buildClick();
                }
                break;
            case 3:
                tvMine.setTextColor(getResources().getColor(R.color.color_4C84FF));
                tvHome.setTextColor(ContextCompat.getColor(context,R.color.color_8D92A3));
                tvOrderSearch.setTextColor(ContextCompat.getColor(context,R.color.color_8D92A3));
                tvBuild.setTextColor(ContextCompat.getColor(context,R.color.color_8D92A3));
                imgOrderSearch.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_news_unselection_iconxhdpi));
                imgHome.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_news_unselection_iconxhdpi));
                imgBuild.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_me_unselection_iconxhdpi));
                imgMine.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_me_selection_iconxhdpi));
                if (homeBottomClick != null) {
                    homeBottomClick.mineClick();
                }
                break;
            default:
                break;
        }
    }

    public void showSelfDot(boolean isVisible) {
        if (isVisible) {
            imgRedTopHome.setVisibility(VISIBLE);
        } else {
            imgRedTopHome.setVisibility(GONE);
        }
    }


    private HomeBottomClick homeBottomClick;

    public void setHomeBottomClick(HomeBottomClick homeBottomClick) {
        this.homeBottomClick = homeBottomClick;
    }

    @OnClick({R.id.rl_home, R.id.rl_order_search, R.id.rl_build, R.id.rl_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_home:
                switchBottom(0);
                break;
            case R.id.rl_order_search:
                switchBottom(1);
                break;
            case R.id.rl_build:
                switchBottom(2);
                break;
            case R.id.rl_mine:
                switchBottom(3);
                break;
            default:
                break;
        }
    }

    public interface HomeBottomClick {
        /**
         * 点击首页
         */
        void homeClick();

        /**
         * 点击订单搜索
         */
        void orderSearchClick();

        /**
         * 点击楼宇信息
         */
        void buildClick();

        /**
         * 点击我的
         */
        void mineClick();
    }
}
