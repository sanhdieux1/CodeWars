package com.kail.encryption.model;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class MyUser implements BaseUser {
    private long userId;
    private MyPermission permission;
    private long expiry;

    @Override
    public long getUserId() {
        return userId;
    }

    @Override
    public MyPermission getPermission() {
        return permission;
    }

    @Override
    public long getExpiry() {
        return expiry;
    }
}
