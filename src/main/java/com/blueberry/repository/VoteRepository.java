package com.blueberry.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blueberry.model.Post;
import com.blueberry.model.User;
import com.blueberry.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

	Optional<Vote> findByPostAndUserOrderByVoteDesc(Post post, User user);
	
}
