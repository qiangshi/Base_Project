package com.staff.canteen.api.utils;

/**
 *
 * @author zenghaiqiang
 * @date 2018/5/28
 * 描述：String判null
 */
public class StringUtils {

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return str != null && str.length() > 0;
    }


}
