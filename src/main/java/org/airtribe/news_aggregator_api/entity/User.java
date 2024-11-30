package org.airtribe.news_aggregator_api.entity;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	private String name;

	@Email
	private String email;

	private String password;

	private boolean isEnabled;

	private Role role;

	@ElementCollection
	@Enumerated(EnumType.STRING) // Store enums as Strings
	private List<Language> languagePreference;

	@ElementCollection
	@Enumerated(EnumType.STRING)
	private List<Topic> topicPreference;

	public User() {
	}

	public User(Long userId, String name, String email, String password, boolean isEnabled, Role role, List<Language> languagePreference, List<Topic> topicPreference) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.isEnabled = isEnabled;
		this.role = role;
		this.languagePreference = languagePreference;
		this.topicPreference = topicPreference;
	}
}