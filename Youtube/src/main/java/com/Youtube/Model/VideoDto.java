package com.Youtube.Model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDto {

	private Long id;
	private Long userId;
	private String name;
	private String description;
	private Status videoStatus;
	private List<String> tags;
	private String thumbnailUrl;
	private String url;
}
