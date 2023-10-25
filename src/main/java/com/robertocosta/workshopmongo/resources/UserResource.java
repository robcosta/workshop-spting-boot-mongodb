package com.robertocosta.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robertocosta.workshopmongo.domain.User;
import com.robertocosta.workshopmongo.dto.UserDTO;
import com.robertocosta.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService service;

	//@RequestMapping(method=RequestMethod.GET)	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> list = service.findAll(); 
		List<UserDTO> listDto = list.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);		
	}
}
