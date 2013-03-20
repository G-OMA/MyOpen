package com.xxx.planeap.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.xxx.planeap.domain.Permission;
import com.xxx.planeap.domain.Role;
import com.xxx.planeap.domain.User;

public class SecurityContextUtil {
	static ThreadLocal<User> currentUser = new ThreadLocal<User>();

	public static void setCurrentUser(User user) {
		currentUser.set(user);
	}

	public static User getCurrentUser() {
		return currentUser.get();
	}

	public static boolean hasPerm(String action, String actionName) {
		User user = getCurrentUser();
		for (Role role : user.getRoles()) {
			for (Permission perm : role.getPermissions()) {
				if (action.equalsIgnoreCase(perm.getAction())) {
					String[] expressions = perm.getExpression().split(",");
					for (String expression : expressions) {
						Pattern p = Pattern.compile(expression);
						Matcher m = p.matcher(actionName);
						if (m.find())
							return true;
					}
				}
			}
		}
		return false;
	}
}
