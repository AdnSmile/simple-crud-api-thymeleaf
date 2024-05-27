package com.berijalan.technicaltest.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "buah")
public class Buah {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nama;

    @Column(length = 100)
    private String warna;

    @Column(length = 256)
    private String deskripsi;

    @Column
    private int berat;

    @Column(length = 100)
    private String asal;

    @Column
    private boolean isDeleted = false;

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Buah(Integer id, String nama, String warna, String deskripsi, int berat, String asal, boolean isDeleted) {
        this.id = id;
        this.nama = nama;
        this.warna = warna;
        this.deskripsi = deskripsi;
        this.berat = berat;
        this.asal = asal;
        this.isDeleted = isDeleted;
    }

    public Buah() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getBerat() {
        return berat;
    }

    public void setBerat(int berat) {
        this.berat = berat;
    }

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }
}
