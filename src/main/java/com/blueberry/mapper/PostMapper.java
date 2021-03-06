package com.blueberry.mapper;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.blueberry.dto.PostRequest;
import com.blueberry.dto.PostResponse;
import com.blueberry.model.Post;
import com.blueberry.model.SubReddit;
import com.blueberry.model.User;
import com.blueberry.model.Vote;
import com.blueberry.model.VoteType;
import com.blueberry.repository.CommentRepository;
import com.blueberry.repository.VoteRepository;
import com.blueberry.service.AuthService;
import com.github.marlonlom.utilities.timeago.TimeAgo;

@Mapper(componentModel = "spring")
public abstract class PostMapper {
	
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthService authService;


    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "user", source = "user")
    public abstract Post map(PostRequest postRequest, SubReddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    @Mapping(target = "upVote", expression = "java(isPostUpVoted(post))")
    @Mapping(target = "downVote", expression = "java(isPostDownVoted(post))")
    public abstract PostResponse mapToDto(Post post);

    
    public String getDuration(Post post) {
    	return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }
    
    Integer commentCout(Post post) {
    	return commentRepository.findByPost(post).size();
    }
    
    boolean isPostUpVoted(Post post) {
        return checkVoteType(post, VoteType.UPVOTE);
    }

    boolean isPostDownVoted(Post post) {
        return checkVoteType(post, VoteType.DOWNVOTE);
    }
    
    private boolean checkVoteType(Post post, VoteType voteType) {
    	if(authService.isLoggedIn()){
    		Optional<Vote> voteForPostByUser = voteRepository
    							.findByPostAndUserOrderByVoteDesc(post, authService.getCurrentUser());
    		
    		return voteForPostByUser.filter(vote -> vote.getVoteType().equals(voteType))
    				.isPresent();
    	}
    	
    	return false;
    }

}
