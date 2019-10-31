package com.staff.canteen.api.ui.fragment;

import java.lang.reflect.Field;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

/**
 *
 * @author zenghaiqiang
 * @date 2019/1/16
 */
public class BaseFragmentDialog extends DialogFragment{



    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
