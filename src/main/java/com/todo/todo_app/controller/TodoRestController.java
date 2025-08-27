package com.todo.todo_app.controller;

import com.todo.todo_app.entity.Todo;
import com.todo.todo_app.service.TodoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin("*")
public class TodoRestController {
    private final TodoService service;

    public TodoRestController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return service.getAll();
    }

    @PostMapping
    public Todo addTodo(@RequestBody Todo todo) {
        return service.save(todo);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        Todo existing = service.getById(id);
        existing.setTitle(todo.getTitle());
        existing.setCompleted(todo.isCompleted());
        return service.save(existing);
    }

    @DeleteMapping("/{id}")
    public String deleteTodo(@PathVariable Long id) {
        service.delete(id);
        return "Deleted Successfully";
    }

    @PatchMapping("/{id}/toggle")
    public Todo toggleTodo(@PathVariable Long id) {
        return service.toggle(id);
    }
}
