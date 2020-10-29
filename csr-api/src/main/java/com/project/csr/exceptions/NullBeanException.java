package com.project.csr.exceptions;

/**
 * 获取Bean异常
 *
 * @author: bin.tong
 * @date: 2020/7/9 11:27
 **/
public class NullBeanException extends NullPointerException {

    private static final long serialVersionUID = -6505601082279225674L;

    public NullBeanException() {
        super();
    }

    public NullBeanException(String name) {
        super(name + "： 没有获取到Bean");
    }

    public <T> NullBeanException(Class<T> clazz) {
        super(clazz.getClass().getName() + "： 没有获取到Bean");
    }
}
