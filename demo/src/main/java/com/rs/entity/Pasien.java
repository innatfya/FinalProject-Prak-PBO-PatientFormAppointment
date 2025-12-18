package com.rs.entity;

public class Pasien extends Person { 
    private String status;

    public Pasien(String name, String id) {
        super(name, id);
        this.status = "Aktif"; }

    @Override
    public String getRole() {
        return "Pasien"; }

    public void updateStatus(String status) { this.status = status; }
    public String getStatus() { return status; }
    public void printPatientCard() {
        System.out.println("Kartu Pasien: " + getName()); } }