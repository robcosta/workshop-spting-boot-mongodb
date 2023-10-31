package com.robertocosta.workshopmongo.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robertocosta.workshopmongo.domain.Post;
import com.robertocosta.workshopmongo.repository.PostRepository;
import com.robertocosta.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Post not found (id: "+id+")"));
	}
	
	public List<Post> findByTitle(String text){
		return repo.findByTitleContainingIgnoreCase(text);
	}
	
	public List<Post> fullSearch(String text, LocalDateTime minDate, LocalDateTime maxDate){
		//maxDate = maxDate.plusSeconds(86400);//24*60*60
		return repo.fullSearch(text, minDate, maxDate);
	}

}
