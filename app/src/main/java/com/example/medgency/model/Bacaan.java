package com.example.medgency.model;

public class Bacaan {
    private String judul;
    private String publisher;

    public Bacaan() {
    }

    public Bacaan(String judul, String publisher) {
        this.judul = judul;
        this.publisher = publisher;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
