package com.logdetective.serviceb;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Arrays;

@Component
public class ServiceAClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String SERVICE_A_URL = "http://service-a:8080/tasks";

    public List<Task> getAllTasksFromServiceA() {
        Task[] tasks = restTemplate.getForObject(SERVICE_A_URL, Task[].class);
        return tasks != null ? Arrays.asList(tasks) : List.of();
    }
}