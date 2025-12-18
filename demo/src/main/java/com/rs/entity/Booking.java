package com.rs.entity;
import com.rs.service.Bookable;

public class Booking implements Bookable {
    private Pasien patient;
    private Dokter doctor;
    private String day;
    private String time;

    public Booking(Pasien patient, Dokter doctor, String day, String time) {
        this.patient = patient;
        this.doctor = doctor;
        this.day = day;
        this.time = time;
    }

    @Override
    public String getBookingDetails() {
        return  "Pasien : " + patient.getName() + 
                "\nDokter : " + doctor.getName() + 
                "\nJadwal : " + day + ", " + time + " WIB";
    }

    public Pasien getPatient() { 
        return patient;  }

    public String getDay() { return day; }

    public void reschedule(String newDay, String newTime) {
        this.day = newDay;
        this.time = newTime;
    }
}