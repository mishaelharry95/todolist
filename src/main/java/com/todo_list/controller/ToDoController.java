package com.todo_list.controller;

import com.todo_list.dto.TodoItemDto;
import com.todo_list.model.ToDoItem;
import com.todo_list.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {


    @Autowired
    private ToDoService toDoService;


    // Create a new to-do item
    @PostMapping
    public ResponseEntity<ToDoItem> createToDoItem(@RequestBody TodoItemDto toDoItem) {
        return toDoService.createToDoItem(toDoItem);
    }

    // Retrieve all to-do items
    @GetMapping
    public ResponseEntity<List<ToDoItem>> getAllToDoItems() {
        return toDoService.getAllToDoItems();
    }

    // Update a to-do item
    @PutMapping("/{id}")
    public ResponseEntity<ToDoItem> updateToDoItem(@PathVariable Long id, @RequestBody TodoItemDto updatedItem) {
        return toDoService.updateToDoItem(id, updatedItem);
    }

    // Delete a to-do item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDoItem(@PathVariable Long id) {
        return toDoService.deleteToDoItem(id);
    }
}