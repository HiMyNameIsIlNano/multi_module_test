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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
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
