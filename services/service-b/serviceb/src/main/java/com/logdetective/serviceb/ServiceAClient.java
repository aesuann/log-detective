package com.logdetective.serviceb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Arrays;

@Component
public class ServiceAClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${service.a.url}")
    private String serviceAUrl;

    public List<Task> getAllTasksFromServiceA() {
        Task[] tasks = restTemplate.getForObject(serviceAUrl, Task[].class);
        return tasks != null ? Arrays.asList(tasks) : List.of();
    }
}