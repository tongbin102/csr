package com.project.csr.api;

import com.project.csr.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bin.tong
 * @since 2020/12/8 16:06
 **/
@RestController
@RequestMapping(value="/admin")
public class AdminController {

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping(value="/keys/{key}",method= RequestMethod.GET)
    public String redisGet(@PathVariable(value = "key") String key) {
        return redisUtils.getString(key);
    }
    @RequestMapping(value="/keys/{key}",method=RequestMethod.POST)
    public Boolean redisSet(@PathVariable(value = "key") String key,String val) {
        return redisUtils.setString(key, val);
    }
}
