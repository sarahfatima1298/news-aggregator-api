package org.airtribe.news_aggregator_api.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class VerificationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String token;

	private Date expirationDate;

	@OneToOne
	@JoinColumn(name = "userId")
	private User user;

	public VerificationToken() {
	}

	public VerificationToken(Long id, String token, Date expirationDate, User user) {
		this.id = id;
		this.token = token;
		this.expirationDate = expirationDate;
		this.user = user;
	}

	public VerificationToken(String token, User user) {
		long millis = System.currentTimeMillis() + 24 * 60 * 60 * 1000;
		this.user = user;
		this.token = token;
		this.expirationDate = new Date(millis);
	}
}
