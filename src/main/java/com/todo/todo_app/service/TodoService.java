package com.todo.todo_app.service;

import com.todo.todo_app.entity.Todo;
import com.todo.todo_app.repository.TodoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TodoService {
    private final TodoRepository repo;

    public TodoService(TodoRepository repo) {
        this.repo = repo;
    }

    public List<Todo> getAll() { return repo.findAll(); }
    public Todo getById(Long id) { return repo.findById(id).orElseThrow(); }
    public Todo save(Todo todo) { return repo.save(todo); }
    public void delete(Long id) { repo.deleteById(id); }

    public Todo toggle(Long id) {
        Todo todo = getById(id);
        todo.setCompleted(!todo.isCompleted());
        return repo.save(todo);
    }
    public Todo updateTodo(Long id, Todo updatedTodo) {
        Todo todo = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        todo.setTitle(updatedTodo.getTitle());
        todo.setCompleted(updatedTodo.isCompleted());
        return repo.save(todo);
    }


}
