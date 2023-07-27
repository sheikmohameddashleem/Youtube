package com.Youtube.Model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Channel {
	
	private String name;
	private List<Video> updloads;
	private List<PlayLists> playList;

}
