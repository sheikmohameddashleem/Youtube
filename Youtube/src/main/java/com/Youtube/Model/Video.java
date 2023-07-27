package com.Youtube.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

	@OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comments> comments = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	private String url;

	private List<String> tags;

	private Status videoStatus;

	private String thumbnailUrl;

	public Video() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Video(String name, String description, Long likes, Long dislikes, User user, Long views) {
		super();
		this.name = name;
		this.description = description;
		this.likes = (long) 0;
		this.dislikes = (long) 0;
		this.user = user;
		this.views = (long) 0;
	}

}
