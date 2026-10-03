package com.logdetective.serviceb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/summary")
public class SummaryController {

    private static final Logger logger = LoggerFactory.getLogger(SummaryController.class);
    private final ServiceAClient serviceAClient;

    public SummaryController(ServiceAClient serviceAClient) {
        this.serviceAClient = serviceAClient;
    }

    @GetMapping
    public List<Task> getSummary() {
        logger.info("Fetching summary from service-a");
        return serviceAClient.getAllTasksFromServiceA();
    }

    @GetMapping("/detailed-report")
    public String getDetailedReport() {
        logger.info("Requesting detailed report");
        return serviceAClient.fetchDetailedReport();
    }
}