package org.airtribe.news_aggregator_api.controller;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.airtribe.news_aggregator_api.JwtUtil.JwtUtil;
import org.airtribe.news_aggregator_api.entity.Result;
import org.airtribe.news_aggregator_api.entity.Topic;
import org.airtribe.news_aggregator_api.entity.User;
import org.airtribe.news_aggregator_api.model.UserModel;
import org.airtribe.news_aggregator_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import reactor.core.publisher.Mono;


@RestController
public class NewsAggregatorController {
	@Autowired
	private WebClient webClient;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping("/api/preferences")
	public List<Topic> getPreferences(@RequestHeader("Authorization") String token) {
		String email = jwtUtil.getEmailFromToken(token.replace("Bearer ", ""));
		User user = userService.getUsersByName(email).get(0);

		if (user == null || !user.isEnabled()) {
			return Collections.emptyList();
		}

		return user.getTopicPreference();
	}

	@PutMapping("/api/preferences")
	public void updatePreferences(@RequestHeader("Authorization") String token, @Valid @RequestBody List<Topic> preferences) {
		String email = jwtUtil.getEmailFromToken(token.replace("Bearer ", ""));
		User user = userService.getUsersByName(email).get(0);

		if (user == null || !user.isEnabled()) {
			return;
		}

		user.setTopicPreference(preferences);
		userService.updateUser(user);
	}

	@GetMapping("/api/news")
	public Mono<Result> getNews(@RequestHeader("Authorization") String token) {
		String email = jwtUtil.getEmailFromToken(token.replace("Bearer ", ""));
		User user = userService.getUsersByName(email).get(0);

		if (user == null || !user.isEnabled()) {
			return null;
		}

		List<Topic> preferences = user.getTopicPreference();
		String query = preferences.stream().map(Enum::name).collect(Collectors.joining(" OR "));

		return webClient.get()
				.uri("https://newsapi.org/v2/everything?q=keyword&apiKey=717ac5c1c69148e1bbbf7e16e2567d09&q=" + query)
				.retrieve().bodyToMono(Result.class)
				.doFinally(signal -> {
					System.out.println("Received response from News API");
				})
				.doFirst(() -> {
					System.out.println("Calling News API");
				});
	}
}
