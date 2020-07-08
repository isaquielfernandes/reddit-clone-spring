package com.blueberry.dto;

import com.blueberry.model.VoteType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteDTO {
	
	private VoteType voteType;
	private Long postId;

}
