package com.Youtube.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {

	private String comments;
	private int userId;
	private int videoId;
}
