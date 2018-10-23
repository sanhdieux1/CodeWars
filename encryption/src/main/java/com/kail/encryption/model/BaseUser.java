package com.kail.encryption.model;

import lombok.Getter;
import lombok.Setter;


public interface BaseUser {
    long getUserId();
    MyPermission getPermission();
    long getExpiry();
}
