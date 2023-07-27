package com.Youtube.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vidId;
	
	
	@NotNull
	private String name;
	
	@NotNull
	private String description;
	
	private Long likes;
	
	private Long dislikes;
	
	private Long views;
	
	@OneToMany(mappedBy="video",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comments> comments=new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	private String url;
	
	private List<String> tags;
	
	private Status videoStatus;
	
	private String thumbnailUrl;
	
	public Video() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Video(String name, String description, Long likes, Long dislikes, User user,Long views) {
		super();
		this.name = name;
		this.description = description;
		this.likes = (long) 0;
		this.dislikes = (long) 0;
		this.user = user;
		this.views=(long) 0;
	}

	
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public Long getLikes() {
//		return likes;
//	}
//
//	public void setLikes(Long likes) {
//		this.likes = likes;
//	}
//
//	public Long getDislikes() {
//		return dislikes;
//	}
//
//	public void setDislikes(Long dislikes) {
//		this.dislikes = dislikes;
//	}
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	public Long getVidId() {
//		return vidId;
//	}
//
//	public void incrementLike() {
//	    this.likes++;
//	}
//	
//	public void decreLike() {
//		this.dislikes++;
//	}
//	
//
//	public Long getViews() {
//		return views;
//	}
//
//	public void setViews(Long views) {
//		this.views = views;
//	}
//
//	@Override
//	public String toString() {
//		return "Video [vidId=" + vidId + ", name=" + name + ", description=" + description + ", likes=" + likes
//				+ ", dislikes=" + dislikes + ", user=" + user + "views="+ views+"]";
//	}
//	
	
}
