# Exercise: XML Mapping with Spring OXM

## Overview

**Mode:** Implementation (Code Lab)
**Time Estimate:** 1.5 - 2 Hours

**Scenario:** You are building an integration component for an older banking system that only accepts data in XML format. You need to take incoming `Transaction` objects in your Java application and marshal them into strictly formatted XML strings.

## Your Goal

Define a POJO, configure a JAXB marshaller, and convert objects to XML and back. You must validate your output by writing tests.

---

## Starter Code Setup

Navigate to the `/starter_code` directory. We have provided a basic Spring Boot application skeleton with the necessary JAXB dependencies already in the `pom.xml`.

---

## Core Tasks

### Task 1: Create the Annotated POJO

1. In the `model` package, create a `Transaction` class.
2. It must have the following fields:
   - `String transactionId`
   - `double amount`
   - `String currency`
   - `String timestamp`
3. Use JAXB annotations (`@XmlRootElement`, `@XmlElement`) so that the resulting XML looks exactly like this:

```xml
<bank-transaction>
    <tx-id>TXN-98765</tx-id>
    <total-amount>1050.50</total-amount>
    <currency-code>USD</currency-code>
    <time>2023-10-27T14:32:00Z</time>
</bank-transaction>
```

### Task 2: Configure the Marshaller

1. In the `config` package, create an `OxmConfig` class.
2. Define a `@Bean` method that returns a `Jaxb2Marshaller`.
3. Configure it to bind your `Transaction` class.

### Task 3: Build the Processing Service

1. In the `service` package, implement the `XmlProcessingService` (a stub is provided).
2. Inject the `Marshaller` and `Unmarshaller` (remember, `Jaxb2Marshaller` implements both).
3. Implement `String convertToXml(Transaction tx)`.
4. Implement `Transaction convertToObject(String xml)`.

### Task 4: Validate with Tests

1. Open the `XmlProcessingServiceTest` class in the `src/test/java` directory.
2. Write a test case that creates a `Transaction`, marshals it, unmarshals the string back to a new `Transaction` object, and asserts that the `amount` of the new object matches the original.

---

## Definition of Done

- The `Transaction` POJO uses custom element names (e.g., `<tx-id>` instead of `<transactionId>`).
- The `XmlProcessingService` successfully handles both directions of conversion.
- All JUnit tests pass successfully.
