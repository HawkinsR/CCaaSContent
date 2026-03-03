package com.example.demoapp.config;

import com.example.demoapp.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * Configuration for Spring OXM.
 * The Jaxb2Marshaller implements BOTH Marshaller and Unmarshaller,
 * so a single bean handles both directions.
 */
@Configuration
public class OxmConfig {

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Product.class);
        return marshaller;
    }
}
