package com.spring.test.service.aopcase;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by xuyifei on 2017/9/24.
 */
@Data
@Service
@Qualifier("testBean")
public class TestBeanAopTest implements TestBeanInterFace{
    private String testStr = "testStr";

    public void test() {
        System.out.println("test process");

    }
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanFactoryTest.xml");

        TestBeanInterFace testBeanAopTest = context.getBean(TestBeanInterFace.class);

        testBeanAopTest.test();

        context.close();
    }

}
