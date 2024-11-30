package org.airtribe.news_aggregator_api.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Article {
	private String author;

	private String title;

	private String description;

	private String content;

	private Source source;

	public Article() {
	}

	public Article(String author, String title, String description, String content, Source source) {
		this.author = author;
		this.title = title;
		this.description = description;
		this.content = content;
		this.source = source;
	}
}
