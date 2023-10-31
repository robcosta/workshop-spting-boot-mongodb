package com.robertocosta.workshopmongo.resources;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.robertocosta.workshopmongo.domain.Post;
import com.robertocosta.workshopmongo.resources.util.URL;
import com.robertocosta.workshopmongo.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {
	
	@Autowired
	private PostService service;

	@GetMapping(value="/{id}")	
	public ResponseEntity<Post> findBiId(@PathVariable String id){
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/titlesearch")	
	public ResponseEntity<List<Post>> findBiTitle(@RequestParam(value="text", defaultValue="") String text){
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/fullsearch")	
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value="text", defaultValue="") String text,
			@RequestParam(value="minDate", defaultValue="") String minDate,
			@RequestParam(value="maxDate", defaultValue="") String maxDate){
		text = URL.decodeParam(text);
		LocalDateTime min = URL.convertDate(minDate, LocalDateTime.parse("0001-01-01T00:00:00"));
		LocalDateTime max = URL.convertDate(maxDate, LocalDateTime.now());
		
		System.out.println("maxDate ="+maxDate);
		System.out.println("max     ="+max);
		
		
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}

}
