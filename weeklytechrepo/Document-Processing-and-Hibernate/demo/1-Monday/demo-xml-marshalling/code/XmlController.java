package com.example.demoapp.controller;

import com.example.demoapp.model.Product;
import com.example.demoapp.service.XmlService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller demonstrating XML marshalling and unmarshalling.
 */
@RestController
@RequestMapping("/api/xml")
public class XmlController {

    private final XmlService xmlService;

    public XmlController(XmlService xmlService) {
        this.xmlService = xmlService;
    }

    /**
     * GET /api/xml/marshal
     * Marshals a sample Product to XML and returns it.
     */
    @GetMapping(value = "/marshal", produces = MediaType.APPLICATION_XML_VALUE)
    public String marshalProduct() throws Exception {
        Product product = new Product(1, "Spring Widget", 19.99);
        return xmlService.marshalToXml(product);
    }

    /**
     * POST /api/xml/unmarshal
     * Accepts raw XML in the request body, unmarshals it to a Product,
     * and returns the Product as JSON.
     */
    @PostMapping(value = "/unmarshal", consumes = MediaType.APPLICATION_XML_VALUE)
    public Product unmarshalProduct(@RequestBody String xml) throws Exception {
        return xmlService.unmarshalFromXml(xml);
    }
}
