package com.staff.canteen.api.ui.fragment.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.staff.canteen.api.R;

import java.util.Objects;

import androidx.fragment.app.DialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 * @author zenghaiqiang
 * @date 2018/5/7
 * 加载页面
 */
public class LoadingFragment extends DialogFragment {

    @BindView(R.id.image_loading)
    ImageView imageLoading;
    Unbinder unbinder;
    private View view;
    private Dialog dialog;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        dialog = new Dialog(Objects.requireNonNull(getActivity()));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.fragment_load, null, false);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        //设置对话框内部的把背景为透明
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        unbinder = ButterKnife.bind(this, view);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        //设置对话框外部的背景为透明
        Window window = dialog.getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;
        window.setAttributes(windowParams);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (view == null) {
            return;
        }
        final AnimationDrawable animationDrawable = (AnimationDrawable) imageLoading.getBackground();
        animationDrawable.start();
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener((view, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
                animationDrawable.stop();
                dismissAllowingStateLoss();
                return true;
            }
            return false;
        });
    }

}
