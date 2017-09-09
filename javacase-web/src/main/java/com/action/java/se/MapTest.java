package com.action.java.se;


/**
 * Created by xuyifei on 2017/1/10.
 */
public class MapTest {
    public static void main(String[] args ) {
        String key = "keyOfRedis";
        for (int i=0;i<10;i++) {
            System.out.println((key+i).hashCode());
        }
    }
}
