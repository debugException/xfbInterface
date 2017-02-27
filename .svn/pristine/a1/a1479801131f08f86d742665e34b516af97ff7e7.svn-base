package cn.emagsoftware.frame.exception;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    private String errorCode;

    private String errorDesc;

    public ServiceException(String errorDesc, String errorCode) {
        super(errorDesc);
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public ServiceException(String errorDesc, String errorCode, Throwable cause) {
        super(errorDesc, cause);
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public ServiceException(String errorDesc, Throwable cause) {
        super(errorDesc, cause);
        this.errorDesc = errorDesc;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return this.errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}