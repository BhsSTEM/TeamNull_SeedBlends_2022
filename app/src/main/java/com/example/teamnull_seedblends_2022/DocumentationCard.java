package com.example.teamnull_seedblends_2022;

public class DocumentationCard {
    private String associatedField;
    private String date;
    private String documentation;

    public DocumentationCard(String af, String dt, String doc) {
        associatedField = af;
        date = dt;
        documentation = doc;
    }

    public String getAssociatedField() {
        return associatedField;
    }

    public String getDate() {
        return date;
    }

    public String getDocumentation() {
        return documentation;
    }

}
