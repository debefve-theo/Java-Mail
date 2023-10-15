package org.example;

public class Piece {
    private String nom;
    private byte[] bytes;

    public Piece(String nom, byte[] bytes) {
        this.nom = nom;
        this.bytes = bytes;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
