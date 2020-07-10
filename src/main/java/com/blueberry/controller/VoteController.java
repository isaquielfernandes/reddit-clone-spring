package com.blueberry.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blueberry.dto.VoteDTO;
import com.blueberry.service.VoteService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/votes/")
@AllArgsConstructor
public class VoteController {

	private final VoteService voteService;
	
	public ResponseEntity<Void> vote(@RequestBody VoteDTO voteDTO) {
		voteService.vote(voteDTO);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
