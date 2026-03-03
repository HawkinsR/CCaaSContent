package com.example.demoapp.model;

import java.io.Serializable;

/**
 * Document model for JMS ObjectMessage demo.
 * Must implement Serializable to be sent via ObjectMessage.
 */
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    private int documentId;
    private String title;
    private String type; // e.g., "invoice", "report", "memo"

    public Document() {
    }

    public Document(int documentId, String title, String type) {
        this.documentId = documentId;
        this.title = title;
        this.type = type;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Document{id=" + documentId + ", title='" + title + "', type='" + type + "'}";
    }
}
