package com.logdetective.servicea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    private final Map<Long, Task> tasks = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    @GetMapping
    public Collection<Task> getAllTasks() {
        logger.info("Fetching tasks, count={}", tasks.size());
        return tasks.values();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        Task task = tasks.get(id);
        if (task == null) {
            logger.warn("Task not found: {}", id);
        } else {
            logger.info("Fetched task, id={}", id);
        }
        return task;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        long id = idCounter.incrementAndGet();
        task.setId(id);
        tasks.put(id, task);
        logger.info("Created task {}", id, task.getTitle());
        return task;
    }
}