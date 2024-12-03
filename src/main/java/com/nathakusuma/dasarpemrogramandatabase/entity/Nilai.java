package com.nathakusuma.dasarpemrogramandatabase.entity;

public class Nilai {
    private String nim;
    private double nilai;

    public Nilai(String nim, double nilai) {
        this.nim = nim;
        this.nilai = nilai;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }
}
