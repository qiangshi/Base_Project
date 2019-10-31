package com.staff.canteen.api.net;

import com.staff.canteen.api.net.exception.ApiException;

import io.reactivex.functions.Function;

/**
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/3/1 10:24
 */
public class ResponseConvert<E> implements Function<BaseApiResponse<E>, BaseApiResponse<E>> {
    @Override
    public BaseApiResponse<E> apply(BaseApiResponse<E> response) {
        if (!response.getCode().equals("success")) {
            throw new ApiException(response.getCode(), response.getMsg());
        }
        return response;
    }
}
