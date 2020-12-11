package com.project.csr.common.exceptions;


import com.project.csr.common.enums.ResCodeEnum;
import lombok.Data;

/**
 * Business Service Exception
 *
 * @author bin.tong
 * @since 2020/7/31 14:01
 **/
@Data
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -3338367685638158964L;

    private ResCodeEnum resCodeEnum;

    public ServiceException(String message) {
        super(message);
        this.resCodeEnum = ResCodeEnum.RESCODE_BAD_REQUEST;
    }

    public ServiceException(ResCodeEnum resCodeEnum) {
        super(resCodeEnum.getStatusName());
        this.resCodeEnum = resCodeEnum;
    }

    public ServiceException(ResCodeEnum resCodeEnum, String msg) {
        super(msg);
        this.resCodeEnum = resCodeEnum;
    }

    public ServiceException(ResCodeEnum resCodeEnum, Throwable cause) {
        super(cause);
        this.resCodeEnum = resCodeEnum;
    }

    public ServiceException(String msg, Throwable cause) {
        super(msg, cause);
        this.resCodeEnum = ResCodeEnum.RESCODE_BAD_REQUEST;
    }

    /**
     * for better performance
     *
     * @return Throwable
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    public Throwable doFillInStackTrace() {
        return super.fillInStackTrace();
    }
}
