package com.liveclips.soccer.commons;

public enum UserTypeEnum {
	FACEBOOKUSER("FACEBOOKUSER"), GUESTUSER("GUESTUSER"), LIVECLIPS("LIVECLIPS");

	private final String code;

	UserTypeEnum(String code) {
		this.code = code;
	}

	public String code() {
		return this.code;
	}

	public static UserTypeEnum fromCode(String c) {
		for (UserTypeEnum v : values()) {
			if (v.code().equals(c)) {
				return v;
			}
		}
		return null;
	}

}
