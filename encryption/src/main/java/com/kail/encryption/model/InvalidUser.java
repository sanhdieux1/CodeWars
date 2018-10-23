package com.kail.encryption.model;

import lombok.ToString;

@ToString
public class InvalidUser implements BaseUser {
    public static InvalidUser INSTANCE = new InvalidUser();
    private InvalidUser(){
    }
    @Override
    public long getUserId() {
        return 0;
    }

    @Override
    public MyPermission getPermission() {
        return MyPermission.ANYONE;
    }

    @Override
    public long getExpiry() {
        return 0;
    }
}
