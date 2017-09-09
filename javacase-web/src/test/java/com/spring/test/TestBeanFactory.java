package com.spring.test;

import com.spring.test.service.TestService;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by xuyifei on 2017/9/9.
 */

public class TestBeanFactory {

    @Test
    public void testBean() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanFactoryTest.xml"));
        TestService testService = (TestService) bf.getBean("testBean");
        testService.printS();
        System.out.println("xx");
    }
}
