package com.Youtube.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Comments {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="video_id")
	private Video video;
	
	@NotNull
	@ManyToOne
	private User user;
	
	@NotNull
	private String description;
	
	private LocalDateTime dateAndTime;
	
	private Long likes;
	
	private Long dislikes;

	public Comments(Video video, @NotNull User user, @NotNull String description, Long likes, Long dislikes) {
		super();
		this.video = video;
		this.user = user;
		this.description = description;
		this.likes = (long) 0;
		this.dislikes = (long) 0;
	}

	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getLikes() {
		return likes;
	}

	public void setLikes(Long likes) {
		this.likes = likes;
	}

	public Long getDislikes() {
		return dislikes;
	}

	public void setDislikes(Long dislikes) {
		this.dislikes = dislikes;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Comments [id=" + id + ", video=" + video + ", user=" + user + ", description=" + description
				+ ", likes=" + likes + ", dislikes=" + dislikes + "]";
	}
	
	
	
	
}
