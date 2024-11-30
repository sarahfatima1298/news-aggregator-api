package org.airtribe.news_aggregator_api.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
	private String status;

	private Integer totalResults;

	private List<Article> articles;

	public Result() {
	}

	public Result(String status, Integer totalResults, List<Article> articles) {
		this.status = status;
		this.totalResults = totalResults;
		this.articles = articles;
	}
}
