package com.logdetective.servicea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
    private static final List<byte[]> reportCache = new ArrayList<>();

    @PostMapping("/generate")
    public String generateReport() {
        logger.info("Generating report");
        byte[] reportData = new byte[10_000_000];
        reportCache.add(reportData);
        logger.debug("Cache size: {}", reportCache.size());
        return "Report generated";
    }

    @GetMapping("/detailed")
    public String generateDetailedReport() throws InterruptedException {
        logger.info("Building detailed report");
        Thread.sleep(15000);
        logger.info("Detailed report ready");
        return "Detailed report generated";
    }
}