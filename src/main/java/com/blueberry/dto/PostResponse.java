package com.blueberry.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

	private Long Id;
	private String postName;
	private String url;
	private String description;
	private String userName;
	private String subredditName;
	private Integer commentCount;
	private Integer voteCount;
	private String duration;
	private boolean upVote;
	private boolean downVote;
	
}
