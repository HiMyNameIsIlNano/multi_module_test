package com.daniele.mybackend.userProfile.dto;

import com.daniele.mydatabase.userProfile.model.Comment;
import org.jooq.Record2;
import org.jooq.generated.tables.UserComment;

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
		String text = comment.getText();
		String topic = comment.getTopic();
		return new CommentDto(text, topic);
	}

	public static CommentDto ofResult(Record2<String,String> comment) {
		String text = comment.get(UserComment.USER_COMMENT.COMMENT_TEXT);
		String topic = comment.get(UserComment.USER_COMMENT.COMMENT_TOPIC);
		return new CommentDto(text, topic);
	}
}
