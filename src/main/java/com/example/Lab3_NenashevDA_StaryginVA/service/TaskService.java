package com.example.Lab3_NenashevDA_StaryginVA.service;

import com.example.Lab3_NenashevDA_StaryginVA.dto.TaskRequest;
import com.example.Lab3_NenashevDA_StaryginVA.model.Task;
import com.example.Lab3_NenashevDA_StaryginVA.model.User;
import com.example.Lab3_NenashevDA_StaryginVA.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByUser_Id(userId);
    }

    public Task createTask(TaskRequest request) {
        User user = userService.getUserById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setUser(user);
        task.setCompleted(request.getCompleted() != null ? request.getCompleted() : false);

        return taskRepository.save(task);
    }

    public Optional<Task> updateTask(Long id, Task updated) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(updated.getTitle());
            task.setCompleted(updated.getCompleted());

            if (updated.getUser() != null && updated.getUser().getId() != null) {
                task.setUser(updated.getUser());
            }
            return taskRepository.save(task);
        });
    }

    public boolean deleteTask(Long id) {
        return taskRepository.findById(id).map(task -> {
            taskRepository.delete(task);
            return true;
        }).orElse(false);
    }
}