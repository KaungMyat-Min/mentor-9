package com.trio.mentor9.model;

public class Quote {
	private String quote_body;
	private String quote_author;
	private String quote_type;
	private String quote_mm;
	
	public String getQuote_mm() {
		return quote_mm;
	}

	public void setQuote_mm(String quote_mm) {
		this.quote_mm = quote_mm;
	}

	public String getQuote_body() {
		return quote_body;
	}

	public String getQuote_author() {
		return quote_author;
	}

	public String getQuote_type() {
		return quote_type;
	}

	public void setQuote_body(String quote_body) {
		this.quote_body = quote_body;
	}

	public void setQuote_author(String quote_author) {
		this.quote_author = quote_author;
	}

	public void setQuote_type(String quote_type) {
		this.quote_type = quote_type;
	}

}
