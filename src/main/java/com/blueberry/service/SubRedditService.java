package com.blueberry.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blueberry.dto.SubRedditDTO;
import com.blueberry.exceptions.SpringRedditException;
import com.blueberry.mapper.SubRedditMapper;
import com.blueberry.model.SubReddit;
import com.blueberry.repository.SubRedditRepository;

import static java.util.stream.Collectors.toList;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubRedditService {

	private final SubRedditRepository subRedditRepository;
	private final SubRedditMapper subRedditMapper;
	
	@Transactional
	public SubRedditDTO save(SubRedditDTO subRedditDTO) {
		SubReddit subReddit = subRedditRepository.save(subRedditMapper.mapDtoToSubReddit(subRedditDTO));
		subRedditDTO.setId(subReddit.getId());
		return subRedditDTO;
	}
	
	@Transactional(readOnly = true)
	public List<SubRedditDTO> getAll(){
		return subRedditRepository.findAll()
				.stream()
				.map(subRedditMapper::mapSubRedditToDto)
				.collect(toList());
	}
	
	public SubRedditDTO getSubReddit(Long id) {
		SubReddit subReddit = subRedditRepository.findById(id)
				.orElseThrow(() -> new SpringRedditException("No subreddit found with ID - " + id));
		
		return subRedditMapper.mapSubRedditToDto(subReddit);
	}
}
