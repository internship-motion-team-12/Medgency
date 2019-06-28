package com.example.medgency.model;

public class User {
    private String NamaDepan;
    private String NamaBelakang;
    private String JenisKelamin; // "pria" atau "wanita"
    private String email;
    private String password;

    public User() {
    }

    public User(String namaDepan, String namaBelakang, String jenisKelamin, String email, String password) {
        NamaDepan = namaDepan;
        NamaBelakang = namaBelakang;
        JenisKelamin = jenisKelamin;
        this.email = email;
        this.password = password;
    }

    public User(String namaDepan, String namaBelakang, String jenisKelamin) {
        NamaDepan = namaDepan;
        NamaBelakang = namaBelakang;
        JenisKelamin = jenisKelamin;
    }

    public String getNamaDepan() {
        return NamaDepan;
    }

    public void setNamaDepan(String namaDepan) {
        NamaDepan = namaDepan;
    }

    public String getNamaBelakang() {
        return NamaBelakang;
    }

    public void setNamaBelakang(String namaBelakang) {
        NamaBelakang = namaBelakang;
    }

    public String getJenisKelamin() {
        return JenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        JenisKelamin = jenisKelamin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
