package com.staff.canteen.api.ui.fragment.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.staff.canteen.api.R;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * 更多任务fragment
 * Created on 2017/12/13.
 *
 * @author zenghaiqiang
 */
public class TaskMoreFragment extends DialogFragment {

    private OnTaskClickListener listener;

    public OnTaskClickListener getListener() {
        return listener;
    }

    public void setListener(OnTaskClickListener listener) {
        this.listener = listener;
    }

    public interface OnTaskClickListener {
        void onClickFinish();

        void onClickDelete();

        void onClickEdit();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (listener != null) {
                switch (v.getId()) {
                    case R.id.v_delete_task:
                        listener.onClickDelete();
                        break;
                    case R.id.v_edit_task:
                        listener.onClickEdit();
                        break;
                    case R.id.v_finish_task:
                        listener.onClickFinish();
                        break;
                    default:
                        break;
                }
                dismissAllowingStateLoss();
            }
        }
    };

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(Objects.requireNonNull(getContext()), R.style.UpDownDialogStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_task_more, null, false);
        rootView.findViewById(R.id.v_delete_task).setOnClickListener(onClickListener);
        rootView.findViewById(R.id.v_edit_task).setOnClickListener(onClickListener);
        rootView.findViewById(R.id.v_finish_task).setOnClickListener(onClickListener);
        rootView.findViewById(R.id.tv_cancel).setOnClickListener(onClickListener);

        dialog.setContentView(rootView);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(attributes);
        window.setGravity(Gravity.BOTTOM);
        dialog.setCanceledOnTouchOutside(true);

        return dialog;
    }
}
