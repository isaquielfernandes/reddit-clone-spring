package com.blueberry.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.blueberry.dto.SubRedditDTO;
import com.blueberry.model.Post;
import com.blueberry.model.SubReddit;

@Mapper(componentModel = "spring")
public interface SubRedditMapper {

	@Mapping(target = "numberOfPosts", expression = "java(mapPosts(subReddit.getPosts()))")
	SubRedditDTO mapSubRedditToDto(SubReddit subReddit);
	
	default Integer mapPosts(List<Post> numberOfPosts) {
		return numberOfPosts.size();
	}
	
	@InheritInverseConfiguration
	@Mapping(target = "posts", ignore = true)
	SubReddit mapDtoToSubReddit(SubRedditDTO subRedditDTO);
}
