package com.project.csr.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.project.csr.utils.UserTools;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 自动填充
 *
 * @author: bin.tong
 * @date: 2020/7/9 13:20
 **/
public class AuditMetaObjectHandler implements MetaObjectHandler {

    public static final String CREATE_TIME = "createTime";
    public static final String UPDATE_TIME = "updateTime";
    public static final String CREATOR = "creator";
    public static final String UPDATER = "updater";
    public static final String VALID_IND = "validInd";

    @Autowired
    private UserTools userTools;

    @Override
    public void insertFill(MetaObject metaObject) {

        Date now = new Date();
        if (metaObject.hasSetter(CREATE_TIME)) {
            this.setFieldValByName(CREATE_TIME, now, metaObject);
        }
        if (metaObject.hasSetter(UPDATE_TIME)) {
            this.setFieldValByName(UPDATE_TIME, now, metaObject);
        }
        if (metaObject.hasSetter(VALID_IND)) {
            this.setFieldValByName(VALID_IND, true, metaObject);
        }

        String username = userTools.getCurrentUserName();
        if (metaObject.hasSetter(CREATOR)) {
            this.setFieldValByName(CREATOR, username, metaObject);
        }
        if (metaObject.hasSetter(UPDATER)) {
            this.setFieldValByName(UPDATER, username, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter(UPDATE_TIME)) {
            this.setFieldValByName(UPDATE_TIME, new Date(), metaObject);
        }
        if (metaObject.hasSetter(UPDATER)) {
            String username = userTools.getCurrentUserName();
            this.setFieldValByName(UPDATER, username, metaObject);
        }
    }
}
