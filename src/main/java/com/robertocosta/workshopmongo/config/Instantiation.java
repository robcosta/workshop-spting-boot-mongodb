package com.robertocosta.workshopmongo.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.robertocosta.workshopmongo.domain.Post;
import com.robertocosta.workshopmongo.domain.User;
import com.robertocosta.workshopmongo.dto.AuthorDTO;
import com.robertocosta.workshopmongo.dto.CommentDTO;
import com.robertocosta.workshopmongo.repository.PostRepository;
import com.robertocosta.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
				
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post p1 = new Post(null, LocalDateTime.parse("2018-03-20T21:00:00"),"Partiu viagem","Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post p2 = new Post(null, LocalDateTime.parse("2018-03-21T21:53:07"),"Bom dia","Acordei feliz hoje", new AuthorDTO(maria));
			
		postRepository.saveAll(Arrays.asList(p1,p2));
		
		maria.getPosts().addAll(Arrays.asList(p1,p2));
		
		userRepository.save(maria);
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!", LocalDateTime.parse("2018-03-21T08:20"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite!", LocalDateTime.parse("2018-03-22T11:35"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", LocalDateTime.parse("2018-03-21T23:40"), new AuthorDTO(bob));
		CommentDTO c4 = new CommentDTO("Deus o abençoe!", LocalDateTime.parse("2018-03-22T11:30"), new AuthorDTO(bob));
		
		p1.getComments().addAll(Arrays.asList(c1,c2));
		p2.getComments().addAll(Arrays.asList(c3,c4));
		
		postRepository.save(p1);
		postRepository.save(p2);
	}

}
