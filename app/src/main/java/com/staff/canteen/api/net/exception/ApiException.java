package com.staff.canteen.api.net.exception;

/**
 * @Description: 异常接收类
 * @Author: 曾海强
 * @CreateDate: 2019/3/1 10:24
 */
public class ApiException extends RuntimeException {

    private String errorCode;
    private String errorMessage;

    public ApiException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ApiException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = Integer.toString(errorCode);
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
