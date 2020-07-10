package com.blueberry.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blueberry.dto.CommentsDTO;
import com.blueberry.exceptions.PostNotFoundException;
import com.blueberry.mapper.CommentMapper;
import com.blueberry.model.Comment;
import com.blueberry.model.NotificationEmail;
import com.blueberry.model.Post;
import com.blueberry.model.User;
import com.blueberry.repository.CommentRepository;
import com.blueberry.repository.PostRepository;
import com.blueberry.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {

	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final CommentRepository commentRepository;
	private final AuthService authService;
	private final MailService mailService;
	private final CommentMapper commentMapper;
	private final MailContentBuilder mailContentBuilder;
	private static final String POST_URL = "";
	
	public void save(CommentsDTO commentsDTO) {
		Post post = postRepository.findById(commentsDTO.getPostId())
				.orElseThrow(() -> new PostNotFoundException(commentsDTO.getPostId().toString()));
		
		Comment comment = commentMapper.map(commentsDTO, post, authService.getCurrentUser());
		commentRepository.save(comment);
		
		String msg = mailContentBuilder.build(post.getUser().getUsername() + " posted a comment on your post." + POST_URL);
		 sendCommentNotification(msg, post.getUser());
		
	}
	
	public List<CommentsDTO> getAllCommentsForPost(Long postId){
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new PostNotFoundException(postId.toString()));
		
		return commentRepository.findByPost(post)
				.stream()
				.map(commentMapper::mapToDTO)
				.collect(Collectors.toList());
	}
	
	public List<CommentsDTO> getAllCommentsForUser(String userName){
		
		User user = userRepository.findByUsername(userName)
				.orElseThrow(() -> new UsernameNotFoundException(userName));
		
		return commentRepository.findAllByUser(user)
				.stream()
				.map(commentMapper::mapToDTO)
				.collect(Collectors.toList());
	}
	
	private void  sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }
	
	
}
