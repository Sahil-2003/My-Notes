package com.example.mynotes.Models;

public class PutPDF {
    public String name;
    public String url;

    public PutPDF(){

    }

    public PutPDF(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}