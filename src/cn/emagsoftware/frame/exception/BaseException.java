package cn.emagsoftware.frame.exception;

/**
 * @Title:异常基类
 */
public class BaseException extends Exception {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4946523050223185381L;

    private String errorCode;
    private String errorMsg;

    public BaseException() {
    }

    public BaseException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

}
