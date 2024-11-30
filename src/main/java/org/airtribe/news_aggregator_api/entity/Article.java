package org.airtribe.news_aggregator_api.entity;

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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}
}
