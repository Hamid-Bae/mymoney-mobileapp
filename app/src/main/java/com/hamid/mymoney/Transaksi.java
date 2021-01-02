package com.hamid.mymoney;

public class Transaksi {
    private String jenis;
    private String kategori;
    private String nominal;
    private String tanggal;
    private String created;

    public Transaksi() {

    }

    public Transaksi(String jenis, String kategori, String nominal, String tanggal, String created) {
        this.jenis = jenis;
        this.kategori = kategori;
        this.nominal = nominal;
        this.tanggal = tanggal;
        this.created = created;
    }

    public String getCreated() { return created; }

    public void setCreated(String created) { this.created = created; }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
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

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
