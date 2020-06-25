package com.blueberry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blueberry.model.Post;
import com.blueberry.model.SubReddit;
import com.blueberry.model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByUser(User user);
	
	List<Post> findAllBySubReddit(SubReddit subReddit);
}
