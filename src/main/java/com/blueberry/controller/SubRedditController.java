package com.blueberry.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blueberry.dto.SubRedditDTO;
import com.blueberry.service.SubRedditService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/subreddit/")
@AllArgsConstructor
public class SubRedditController {

	private final SubRedditService subRedditService;
	
	@PostMapping
	public ResponseEntity<SubRedditDTO> createSubReddit(@RequestBody SubRedditDTO subRedditDTO){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(subRedditService.save(subRedditDTO));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SubRedditDTO> getSubReddit(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK)
				.body(subRedditService.getSubReddit(id));
	}
	
	@GetMapping
	public ResponseEntity<List<SubRedditDTO>> getAllSubReddit(){
		return ResponseEntity.status(HttpStatus.OK)
				.body(subRedditService.getAll());
	}
}
