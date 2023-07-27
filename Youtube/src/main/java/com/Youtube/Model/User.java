package com.Youtube.Model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	@Email
	private String email;
	
	@NotNull
	private String username;
	
	@NotNull
	@JsonProperty(access=Access.WRITE_ONLY)
	private String password;
	
	@NotNull
	private String mobileNumber;

	
	private Long subscribers=(long) 0;
	
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "subscribedTo")
	private List<User> subscribedTo=new ArrayList<>();
	
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL,orphanRemoval = true)
	@Column(name="Uploads")
	@JsonIgnore
	private List<Video> videos = new ArrayList<>();
	
	@OneToMany
	@JsonIgnore
	private List<Video> watchLater=new ArrayList<>();
	
	@OneToMany
	@JsonIgnore
	private List<Video> likedVideos=new ArrayList<>();
	
	@OneToMany
	@JsonIgnore
	private List<Video> playList=new ArrayList<>();
	
	@OneToMany
	@JsonIgnore
	private List<Video> History=new ArrayList<>();
	
}
