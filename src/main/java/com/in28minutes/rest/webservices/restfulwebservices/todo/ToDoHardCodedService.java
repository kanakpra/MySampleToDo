package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class ToDoHardCodedService {
	
	private static List<ToDo> todos = new ArrayList<>();
	
	private static long idCounter = 0;
	
	static {
		
		todos.add(new ToDo(++idCounter, "in28minutes", "Learn to dance", new Date(), false));
		todos.add(new ToDo(++idCounter, "in28minutes", "Learn Spanish", new Date(), false));
		todos.add(new ToDo(++idCounter, "in28minutes", "Learn to paint", new Date(), false));
	}
	
	public List<ToDo> findAll(){
		return todos;
	}
	
	public ToDo deleteById(long id) {
		ToDo todo = findById(id);
		if(todo==null)
			return null;
		if(todos.remove(todo)) {
			return todo;
		}
		return null;
	}

	public ToDo findById(Long id) {
		for(ToDo todo :todos) {
			if(todo.getId() == id) {
				return todo;
			}
		}
		return null;
	}
	
	public ToDo save(ToDo todo) {
		if(todo.getId() ==-1 || todo.getId()==0) {
			todo.setId(++idCounter);
			todos.add(todo);
		}
		else
		{
			deleteById(todo.getId());
			todos.add(todo);
		}
		return todo;
	}
	
	

}
