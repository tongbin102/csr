package com.project.csr.common.exceptions;

import com.project.csr.common.enums.ResCodeEnum;

/**
 * @author: bin.tong
 * @date: 2020/11/6 9:20
 * @apiNote 全局异常
 **/
public class GlobalException extends RuntimeException {

    private ResCodeEnum resCodeEnum;

    public GlobalException(ResCodeEnum resCodeEnum) {
        super(resCodeEnum.getStatusName());
        this.resCodeEnum = resCodeEnum;
    }

    public ResCodeEnum getResCodeEnum() {
        return resCodeEnum;
    }
}
