package com.logdetective.servicea;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final Map<Long, Task> tasks = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    @GetMapping
    public Collection<Task> getAllTasks() {
        return tasks.values();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        return tasks.get(id);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        long id = idCounter.incrementAndGet();
        task.setId(id);
        tasks.put(id, task);
        return task;
    }
}