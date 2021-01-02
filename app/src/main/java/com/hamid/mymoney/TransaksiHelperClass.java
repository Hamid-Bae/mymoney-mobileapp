package com.hamid.mymoney;

public class TransaksiHelperClass {
    String kategori, nominal, jenis, tanggal;

    public TransaksiHelperClass(String kategori, String nominal, String jenis, String tanggal) {
        this.kategori = kategori;
        this.nominal = nominal;
        this.jenis = jenis;
        this.tanggal = tanggal;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
