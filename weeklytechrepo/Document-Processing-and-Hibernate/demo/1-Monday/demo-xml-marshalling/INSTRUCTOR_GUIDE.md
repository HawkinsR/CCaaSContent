# Demo: XML Marshalling with Spring OXM

## Overview

**Type:** Code-focused
**Time:** 30-40 minutes
**Goal:** Demonstrate marshalling and unmarshalling Java objects to/from XML using Spring OXM with a JAXB-based marshaller.

---

## Phase 1: The Concept (Brief)

**Time:** 5 minutes

- Reference the written content on XML Processing in Spring.
- Explain: "Marshalling is converting a Java object to XML. Unmarshalling is the reverse -- XML back to a Java object."
- "Spring OXM gives us a consistent API regardless of which underlying XML framework we use. Today we will use JAXB."

---

## Phase 2: The Code (Live Implementation)

**Time:** 25-35 minutes

### Step 1: Add the Dependency

Add the JAXB dependency to `pom.xml` (JAXB is no longer bundled with Java 11+):

```xml
<dependency>
    <groupId>org.glassfish.jaxb</groupId>
    <artifactId>jaxb-runtime</artifactId>
</dependency>
```

### Step 2: Create the Product Model

Open `code/Product.java` and build it live:

- "First, we need a POJO that we want to convert to XML."
- "Notice we annotate with `@XmlRootElement` and `@XmlElement` -- these tell JAXB how to map our fields to XML tags."

### Step 3: Configure the Marshaller Bean

Open `code/OxmConfig.java`:

- "We create a `Jaxb2Marshaller` bean. This one bean acts as both our Marshaller AND Unmarshaller."
- "We tell it which classes to bind by calling `setClassesToBeBound()`."

### Step 4: Create the XML Service

Open `code/XmlService.java`:

- Walk through the `marshalToXml()` method line by line.
- "We create a `StreamResult` that wraps a `StringWriter`. Then we call `marshaller.marshal()` with our object and the result target."
- Walk through `unmarshalFromXml()` similarly.
- "Notice how clean this is -- no manual XML string building."

### Step 5: Create the REST Controller

Open `code/XmlController.java`:

- "This controller has two endpoints: one to convert an object to XML, and one to convert XML back to an object."
- Show how `produces = MediaType.APPLICATION_XML_VALUE` tells Spring to return XML content type.

### Step 6: Run and Test

1. Start the application.
2. **Test marshalling:** `GET http://localhost:8080/api/xml/marshal`
   - Show the XML output in the browser/Postman.
3. **Test unmarshalling:** `POST http://localhost:8080/api/xml/unmarshal`
   - Send XML in the request body:

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <product>
       <id>99</id>
       <name>Custom Widget</name>
       <price>29.99</price>
   </product>
   ```

   - Show that it returns the deserialized object as JSON.

### Step 7: Demonstrate Error Handling

- Send malformed XML (e.g., missing closing tag) and show the `XmlMappingException`.
- "Spring wraps all XML errors in `XmlMappingException` or its subclasses. This gives us a consistent exception hierarchy regardless of the underlying framework."

---

## Key Talking Points

- JAXB annotations (`@XmlRootElement`, `@XmlElement`) control the mapping -- similar to how Jackson annotations control JSON mapping.
- `Jaxb2Marshaller` implements both `Marshaller` and `Unmarshaller` interfaces.
- `XmlMappingException` provides a unified exception hierarchy across XML frameworks.
- "Tomorrow, we will do the same thing with JSON using Jackson and Gson. You will see the patterns are nearly identical."
