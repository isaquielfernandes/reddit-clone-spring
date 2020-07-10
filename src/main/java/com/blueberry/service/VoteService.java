package com.blueberry.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.blueberry.dto.VoteDTO;
import com.blueberry.exceptions.PostNotFoundException;
import com.blueberry.exceptions.SpringRedditException;
import com.blueberry.model.Post;
import com.blueberry.model.Vote;
import com.blueberry.model.VoteType;
import com.blueberry.repository.PostRepository;
import com.blueberry.repository.VoteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VoteService {

	private final VoteRepository voteRepository;
	private final PostRepository postRepository;
	private final AuthService authService;

	public void vote(VoteDTO voteDTO) {
		Post post = postRepository.findById(voteDTO.getPostId())
				.orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDTO.getPostId()));

		Optional<Vote> voteByPostAndUser = voteRepository.findByPostAndUserOrderByVoteDesc(post,
				authService.getCurrentUser());

		if(voteByPostAndUser.isPresent() && voteByPostAndUser.get().getVoteType().equals(voteDTO.getVoteType())) {
			throw new SpringRedditException("You have already " + voteDTO.getVoteType() + "'d for this post");
		}

		if(VoteType.UPVOTE.equals(voteDTO.getVoteType())) {
			post.setVoteCount(post.getVoteCount() + 1);
		} else {
			post.setVoteCount(post.getVoteCount() - 1);
		}

		voteRepository.save(mapToVote(voteDTO, post));
		postRepository.save(post);
	}

	private Vote mapToVote(VoteDTO voteDTO, Post post) {
		return Vote.builder().voteType(voteDTO.getVoteType()).post(post).user(authService.getCurrentUser()).build();
	}
}
