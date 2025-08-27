package com.todo.todo_app.controller;

import com.todo.todo_app.entity.Todo;
import com.todo.todo_app.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/todos")
public class TodoWebController {
    private final TodoService service;

    public TodoWebController(TodoService service) {
        this.service = service;
    }

    // Show all todos
    @GetMapping
    public String listTodos(Model model) {
        model.addAttribute("todos", service.getAll());
        model.addAttribute("todo", new Todo());
        return "todos";
    }

    // Add new todo
    @PostMapping
    public String addTodo(@ModelAttribute Todo todo) {
        service.save(todo);
        return "redirect:/todos";
    }

    // Delete a todo
    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/todos";
    }

    // Toggle completed status
    @GetMapping("/toggle/{id}")
    public String toggleTodo(@PathVariable Long id) {
        service.toggle(id);
        return "redirect:/todos";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String editTodoForm(@PathVariable Long id, Model model) {
        model.addAttribute("todo", service.getById(id));
        return "edit"; // Points to edit.html
    }

    // Handle update
    @PostMapping("/update/{id}")
    public String updateTodo(@PathVariable Long id, @ModelAttribute Todo todo) {
        service.updateTodo(id, todo);
        return "redirect:/todos";
    }
}
