package com.spring.test;

import com.spring.test.service.aopcase.TestBeanAopTest;
import com.spring.test.service.aopcase.TestBeanInterFace;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by xuyifei on 2017/9/9.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:*.xml")
public class TestBeanFactory {

    @Autowired
    TestBeanInterFace testBeanInterFace;


    @Test
    public void testBeanAop() {
        System.out.println("before test");
        testBeanInterFace.test();
    }

}
