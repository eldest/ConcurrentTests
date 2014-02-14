package com.eldest.annotation.permission.test;

@PermissionRequired(User.Permission.USER_MANAGEMENT)
public class UserDeleteAction {
    public void invoke(User user) { /* */ }
}
