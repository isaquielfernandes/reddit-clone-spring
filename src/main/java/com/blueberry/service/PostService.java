package com.blueberry.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blueberry.dto.PostRequest;
import com.blueberry.dto.PostResponse;
import com.blueberry.exceptions.PostNotFoundException;
import com.blueberry.exceptions.SubRedditNotFoundException;
import com.blueberry.mapper.PostMapper;
import com.blueberry.model.Post;
import com.blueberry.model.SubReddit;
import com.blueberry.model.User;
import com.blueberry.repository.PostRepository;
import com.blueberry.repository.SubRedditRepository;
import com.blueberry.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class PostService {

	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final AuthService authService;
	private final PostMapper postMapper;
	private final SubRedditRepository subRedditRepository;
	
	public void save(PostRequest postRequest) {
		SubReddit subReddit = subRedditRepository.findByName(postRequest.getSubredditName())
				.orElseThrow(() -> new SubRedditNotFoundException(postRequest.getSubredditName()));
		
		postRepository.save(postMapper.map(postRequest, subReddit, authService.getCurrentUser()));
	}
	
	@Transactional(readOnly = true)
	public PostResponse getPost(Long id) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new PostNotFoundException(id.toString()));
		
		return postMapper.mapToDto(post);
	}
	
	@Transactional(readOnly = true)
	public List<PostResponse> getAllPosts(){
		return postRepository.findAll()
				.stream()
				.map(postMapper::mapToDto)
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<PostResponse> getPostsByUsername(String username){
		User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
		
		return postRepository.findByUser(user)
				.stream()
				.map(postMapper::mapToDto)
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<PostResponse> getPostsBySubReddit(Long subredditId){
		SubReddit subreddit = subRedditRepository.findById(subredditId)
                .orElseThrow(() -> new SubRedditNotFoundException(subredditId.toString()));
		
        List<Post> posts = postRepository.findAllBySubReddit(subreddit);
        
        return posts.stream().map(postMapper::mapToDto)
				.collect(Collectors.toList());
	}
}
