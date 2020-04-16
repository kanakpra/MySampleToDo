package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TodoJpaResource {
	
	@Autowired
	private ToDoHardCodedService todoService;
	
	@Autowired
	private TodoJpaRepository todoJpaRepository;
	
	@GetMapping("/jpa/users/{username}/todos")
	public List<ToDo> getAllTodos(@PathVariable String username){
		return todoJpaRepository.findByUserName(username);
		//return todoService.findAll();
		
	}
	
	@GetMapping("/jpa/users/{username}/todos/{id}")
	public ToDo getTodo(@PathVariable String username, @PathVariable long id){
		return todoJpaRepository.findById(id).get();
		//return todoService.findById(id);
		
	}
	
	@DeleteMapping("/jpa/users/{username}/todos/{id}") 
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id){
		
		//ToDo todo = todoService.deleteById(id);
			todoJpaRepository.deleteById(id);
			return ResponseEntity.noContent().build();
			
			
	}
	
	@PutMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<ToDo> updateTodo(@PathVariable String username, 
			@PathVariable long id, @RequestBody ToDo todo){
		//ToDo todoUpdated = todoService.save(todo);
		
		ToDo todoUpdated = todoJpaRepository.save(todo);
		return new ResponseEntity<ToDo>(todo, HttpStatus.OK);
		
	}
	
	@PostMapping("/jpa/users/{username}/todos")
	public ResponseEntity<Void> createTodo(@PathVariable String username, @RequestBody ToDo todo){
		todo.setUserName(username);
		ToDo createdTodo = todoJpaRepository.save(todo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	
	
	
	
	

}
