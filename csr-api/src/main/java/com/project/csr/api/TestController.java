package com.project.csr.api;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: bin.tong
 * @date: 2020/10/29 13:39
 **/
@RestController
@RequestMapping(value = "/test")
public class TestController {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);
    @RequestMapping(value = "/hello")
    public String hello(){
        log.info("test hello");
        return "hello";
    }
}
