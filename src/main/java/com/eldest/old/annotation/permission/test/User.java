package com.eldest.old.annotation.permission.test;

import java.util.ArrayList;


public class User {
    public static enum Permission {
        USER_MANAGEMENT, CONTENT_MANAGEMENT
    }

    private ArrayList<Permission> permissions;

    public ArrayList<Permission> getPermissions() {
        return new ArrayList<Permission>(permissions);
    }

    // ...
}
