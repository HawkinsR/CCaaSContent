package com.revature.xml.service;

import com.revature.xml.model.Transaction;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Service;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

@Service
public class XmlProcessingService {

    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    public XmlProcessingService(Marshaller marshaller, Unmarshaller unmarshaller) {
        this.marshaller = marshaller;
        this.unmarshaller = unmarshaller;
    }

    public String convertToXml(Transaction tx) {
        // TODO: Implement using StringWriter and StreamResult
        return null;
    }

    public Transaction convertToObject(String xml) {
        // TODO: Implement using StringReader and StreamSource
        return null;
    }
}
