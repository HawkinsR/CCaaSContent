package com.example.demoapp.controller;

import com.example.demoapp.model.Document;
import com.example.demoapp.service.DocumentProducer;
import org.springframework.web.bind.annotation.*;

/**
 * Controller with endpoints to send different JMS message types.
 */
@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentProducer producer;

    public DocumentController(DocumentProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/text")
    public String sendText(@RequestBody String content) {
        producer.sendText("document-queue", content);
        return "Text message sent";
    }

    @PostMapping("/object")
    public String sendObject() {
        Document doc = new Document(1, "Q4 Report", "report");
        producer.sendObject("object-queue", doc);
        return "Object message sent";
    }

    @PostMapping("/priority")
    public String sendHighPriority(@RequestBody String content) {
        producer.sendWithPriority("priority-queue", content, "HIGH");
        return "High-priority message sent";
    }
}
