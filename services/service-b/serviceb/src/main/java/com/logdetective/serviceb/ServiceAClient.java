package com.logdetective.serviceb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Arrays;

@Component
public class ServiceAClient {

    private final RestTemplate restTemplate;

    @Value("${service.a.url}")
    private String serviceAUrl;

    public ServiceAClient() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(2000);
        factory.setReadTimeout(5000);
        this.restTemplate = new RestTemplate(factory);
    }

    public List<Task> getAllTasksFromServiceA() {
        Task[] tasks = restTemplate.getForObject(serviceAUrl, Task[].class);
        return tasks != null ? Arrays.asList(tasks) : List.of();
    }

    public String fetchDetailedReport() {
        String url = serviceAUrl.replace("/tasks", "/reports/detailed");
        return restTemplate.getForObject(url, String.class);
    }
}