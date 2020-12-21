package com.project.csr;

import com.project.csr.utils.MD5Utils;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: bin.tong
 * @date: 2020/11/5 12:40
 **/
@Slf4j
public class DemoTests {

    @Test
    public void testMd5() {
        String password = "123456";
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        log.info(encoder.encode(password));
    }

    @Test
    public void testToken() {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYwNjI2ODY0NCwiaWF0IjoxNjA2MTgyMjQ0fQ.BLk3JGXpWbp4vbZy_brRdVpLxetME3jBZ5cJw3aefzLWp9uzmf3HOA4kzP0L2mOJmOS2_eTN1xWCEnZ40AxkAw";

    }

    @Test
    public void testGetChannelId() {
        String questionSeriesNo = "F1-1";
        log.info(questionSeriesNo.substring(0, 1));
    }

}
