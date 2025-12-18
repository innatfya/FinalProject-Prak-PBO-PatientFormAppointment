package com.rs.entity;

public class Dokter extends Person {
    private String department;

    public Dokter(String name, String id, String department) {
        super(name, id);
        this.department = department; }

    @Override
    public String getRole() {
        return "Dokter Spesialis"; }

    public String getDepartment() { return department; }
    public void setDepartment(String dept) { this.department = dept; }
    public void greet() {
        System.out.println("Halo, saya " + getName() + " dari spesialis " + department); }
}