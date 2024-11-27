package org.airtribe.news_aggregator_api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {
	private String email;
	private String password;
	private String name;

	public UserModel() {

	}

	public UserModel(String email, String password, String name) {
		this.email = email;
		this.password = password;
		this.name = name;
	}
}
