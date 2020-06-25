package com.blueberry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blueberry.model.Comment;
import com.blueberry.model.Post;
import com.blueberry.model.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByPost(Post post);
	
	List<Comment> findAllByUser(User user);
}
