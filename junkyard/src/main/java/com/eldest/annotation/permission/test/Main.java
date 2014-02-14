package com.eldest.annotation.permission.test;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		User user = new User();
		Class<?> actionClass = UserDeleteAction.class;
		PermissionRequired permissionRequired = actionClass.getAnnotation(PermissionRequired.class);
		if (permissionRequired != null)
			if (user != null && user.getPermissions().contains(permissionRequired.value())) {
				// ��������� ��������
			}

	}

}
