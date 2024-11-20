package com.todo_list.service;

import com.todo_list.dto.TodoItemDto;
import com.todo_list.model.ToDoItem;
import com.todo_list.repository.ToDoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    // Create a new to-do item
    public ResponseEntity<ToDoItem> createToDoItem(TodoItemDto todoItemDto) {
        if (todoItemDto.getTitle() == null || todoItemDto.getTitle().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        ToDoItem toDoItem = new ToDoItem();
        BeanUtils.copyProperties(todoItemDto, toDoItem);
        ToDoItem savedItem = toDoRepository.save(toDoItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
    }

    // Retrieve all to-do items
    public ResponseEntity<List<ToDoItem>> getAllToDoItems() {
        List<ToDoItem> toDoItems = toDoRepository.findAll();
        return ResponseEntity.ok(toDoItems);
    }

    // Update a to-do item
    public ResponseEntity<ToDoItem> updateToDoItem(Long id, TodoItemDto updatedItemDto) {
        Optional<ToDoItem> optionalItem = toDoRepository.findById(id);

        if (optionalItem.isPresent()) {
            ToDoItem existingItem = optionalItem.get();
            existingItem.setTitle(updatedItemDto.getTitle() != null ? updatedItemDto.getTitle() : existingItem.getTitle());
            existingItem.setCompleted(updatedItemDto.isCompleted());
            toDoRepository.save(existingItem);
            return ResponseEntity.ok(existingItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a to-do item
    public ResponseEntity<Void> deleteToDoItem(Long id) {
        if (toDoRepository.existsById(id)) {
            toDoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
