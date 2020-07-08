package com.blueberry.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubRedditDTO {
	
	private Long id;
	private String name;
	private String description;
	private Integer numberOfPosts;

}
