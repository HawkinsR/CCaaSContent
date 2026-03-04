package com.revature.tracingdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RestController
public class TracingDemoApplication {

    private static final Logger log = LoggerFactory.getLogger(TracingDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TracingDemoApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/start-trace")
    public String startTrace() {
        // Because Micrometer Tracing is on the classpath, this log entry
        // will automatically include a unique [TraceID, SpanID] prefix.
        log.info("Request received at the Edge Controller. Trace started.");

        // Simulating some business logic...
        log.info("Processing business logic layer...");

        // Typically, we would use restTemplate here to call a Downstream Service.
        // RestTemplate is automatically instrumented to pass the TraceID header
        // forward.

        log.info("Finished processing request.");
        return "Trace propagation demonstrated. Check console logs!";
    }
}
