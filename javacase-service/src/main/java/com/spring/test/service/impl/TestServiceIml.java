package com.spring.test.service.impl;

import com.spring.test.service.TestService;
import org.springframework.stereotype.Service;

/**
 * Created by xuyifei on 2017/9/9.
 */
@Service
public class TestServiceIml implements TestService{

    private String s;

    public TestServiceIml() {
    }

    public TestServiceIml(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @Override
    public void printS() {
        System.out.println(s);
    }
}
