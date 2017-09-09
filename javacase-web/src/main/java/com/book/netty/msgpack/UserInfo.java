package com.book.netty.msgpack;

import java.io.Serializable;

/**
 * Created by xuyifei on 2017/2/26.
 */
public class UserInfo implements Serializable{
    private static final long serialVersionUID = 1L;
    private String userName;
    private int userID;

    public UserInfo(String userName, int userID) {
        this.userName = userName;
        this.userID = userID;
    }
}
