package com.example.medgency.model;

public class Hospital {
    private String nama;
    private String address;
    private String ShortAddress;
    private String deskripsi;
    private String HospitalType; // Bersalin, jiwa, umum
    private HospitalContact mHospitalContact;

    public Hospital() {
    }

    public Hospital(String nama, String shortAddress, String hospitalType) {
        this.nama = nama;
        ShortAddress = shortAddress;
        HospitalType = hospitalType;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShortAddress() {
        return ShortAddress;
    }

    public void setShortAddress(String shortAddress) {
        ShortAddress = shortAddress;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getHospitalType() {
        return HospitalType;
    }

    public void setHospitalType(String hospitalType) {
        HospitalType = hospitalType;
    }

    public HospitalContact getmHospitalContact() {
        return mHospitalContact;
    }

    public void setmHospitalContact(HospitalContact mHospitalContact) {
        this.mHospitalContact = mHospitalContact;
    }
}
