package com.hrustalyov.model;

import java.io.Serializable;

public class TextFile implements Serializable {

	private String name;
	private String text;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "TextFile{" +
				"name='" + name + '\'' +
				", text='" + text + '\'' +
				'}';
	}
}
