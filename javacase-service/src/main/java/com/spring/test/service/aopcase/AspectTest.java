package com.spring.test.service.aopcase;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

/**
 * Created by xuyifei on 2017/9/24.
 */
@Aspect
@Service
public class AspectTest {
    @Before("execution(* com.spring.test.service.aopcase.TestBeanAopTest.test(..))")
    public void beforeTest() {
        System.out.println("before test");
    }
}
