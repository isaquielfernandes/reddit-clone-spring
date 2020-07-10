package com.blueberry.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.blueberry.dto.CommentsDTO;
import com.blueberry.model.Comment;
import com.blueberry.model.Post;
import com.blueberry.model.User;

@Mapper(componentModel = "spring")
public interface CommentMapper {
	
	@Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentsDTO.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
	Comment map(CommentsDTO commentsDTO, Post post, User user);
	
	@Mapping(target = "postId", expression = "java(comment.getPost().getPostId()")
	@Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
	CommentsDTO mapToDTO(Comment comment);

}
