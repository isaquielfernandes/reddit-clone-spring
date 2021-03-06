package com.blueberry.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blueberry.model.SubReddit;

@Repository
public interface SubRedditRepository extends JpaRepository<SubReddit, Long> {

	Optional<SubReddit> findByName(String name);
}
