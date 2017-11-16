package com.daniele.mybackend.userProfile.dto;

import com.daniele.mydatabase.userProfile.model.Comment;

public class CommentDto {

	private String text;
	private String topic;
	
	private CommentDto(String text, String topic) {
		this.text = text;
		this.topic = topic;
	}

	public String getText() {
		return text;
	}

	public String getTopic() {
		return topic;
	}
	
	public static CommentDto ofComment(Comment comment) {
		return new CommentDto(comment.getText(), comment.getTopic());
	}
}
