package com.example.demoapp.service;

import com.example.demoapp.model.Product;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Service;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Service that demonstrates marshalling (object to XML)
 * and unmarshalling (XML to object) using Spring OXM.
 */
@Service
public class XmlService {

    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    // Both interfaces are satisfied by the single Jaxb2Marshaller bean
    public XmlService(Marshaller marshaller, Unmarshaller unmarshaller) {
        this.marshaller = marshaller;
        this.unmarshaller = unmarshaller;
    }

    /**
     * Converts a Product object to an XML string.
     */
    public String marshalToXml(Product product) throws Exception {
        StringWriter writer = new StringWriter();
        marshaller.marshal(product, new StreamResult(writer));
        return writer.toString();
    }

    /**
     * Converts an XML string back into a Product object.
     */
    public Product unmarshalFromXml(String xml) throws Exception {
        StringReader reader = new StringReader(xml);
        return (Product) unmarshaller.unmarshal(new StreamSource(reader));
    }
}
