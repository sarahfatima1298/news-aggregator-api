package org.airtribe.news_aggregator_api.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.airtribe.news_aggregator_api.entity.User;
import org.airtribe.news_aggregator_api.model.UserModel;
import org.airtribe.news_aggregator_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RegistrationController {

	@Autowired
	private UserService userService;

	@PostMapping("/api/register")
	public User register(@Valid @RequestBody UserModel user, HttpServletRequest request) {
		User storedUser = userService.register(user);
		String token = UUID.randomUUID().toString();
		String applicationUrl = getApplicationUrl(request) + "/api/verifyRegistration?token=" + token;
		userService.createVerificationToken(storedUser, token);
		System.out.println("Verification token created for user: " + storedUser.getEmail());
		System.out.println("Verification url: " + applicationUrl);
		return storedUser;
	}

	@PostMapping("/api/verifyRegistration")
	public String verifyRegistration(@RequestParam String token) {
		boolean isValid = userService.validateTokenAndEnableUser(token);
		if (!isValid) {
			return "Invalid token";
		}
		return "User enabled successfully";
	}

	@PostMapping("/api/login")
	public String login(@RequestParam String email, @RequestParam String password) {
		return userService.login(email, password);
	}

	private String getApplicationUrl(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

	@GetMapping("/api/hello")
	public String hello() {
		return "Hello from news aggregator api";
	}

}
