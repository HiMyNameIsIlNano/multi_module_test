package com.daniele.mydatabase.userProfile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.daniele.mydatabase.shared.model.SlicedEntity;

@Entity
@Table(name="USER_COMMENT")
public class Comment extends SlicedEntity {
	
	@Transient
	private static final long serialVersionUID = -4078057003770178175L;

	@Column(name="comment_text")
	@Lob
	private String text;
	
	@Column(name="comment_topic")
	private String topic;
	
	@ManyToOne
	@JoinColumn(name="user_id_ref")
	private UserProfileDetails user;
	
	public Comment() { }

	private Comment(CommentBuilder builder) {
		this.text = builder.comment.text;
		this.topic = builder.comment.topic;
		this.user = builder.comment.user;
	}

	public String getText() {
		return text;
	}

	public String getTopic() {
		return topic;
	}

    public UserProfileDetails getUser() {
        return user;
    }

	@Override
	public String toString() {
		return "Comment [text=" + text + ", topic=" + topic + "]";
	}

	public static class CommentBuilder {
		
		protected Comment comment;
		
		public static CommentBuilder forCreation() {
			return new CommentBuilder(new Comment());
		}

        public static CommentBuilder forUpdate(Comment comment) {
            return new CommentBuilder(comment);
        }
		
		private CommentBuilder(Comment comment) {
			this.comment = comment;
		}
		
		public CommentBuilder withText(String text) {
			comment.text = text;
			return this;
		}
		
		public CommentBuilder withTopic(String topic) {
			comment.topic = topic;
			return this;
		}

        public CommentBuilder withUser(UserProfileDetails user) {
            comment.user = user;
            return this;
        }

		public Comment build() {
			return new Comment(this);
		}
		
	}
	
}
