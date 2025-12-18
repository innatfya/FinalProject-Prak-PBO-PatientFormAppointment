package com.rs;

import java.util.Scanner;

import com.rs.entity.Booking;
import com.rs.entity.Dokter;
import com.rs.entity.Pasien;

public class HospitalMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=");
        System.out.println("................Welcome To Bamjji Hospitals!...............");
        System.out.println("Formulir Janji Temu Dokter RS Bamjji");
        System.out.println("-----------------------------------------------------------");

        System.out.print("Nama Pasien : ");
        String nama = input.nextLine();
        System.out.print("ID Pasien   : ");
        String id = input.nextLine();
        System.out.println("-----------------------------------------------------------");

        System.out.println("Pilih Departemen: ");
        System.out.println("1. Toraks & Kardiovaskular\n2. Neurologi\n3. Pulmonologi\n4. Umum\n5. Bedah\n6. Anak\7. Kulit & Kelamin\n8. Gigi\n9. Psikiatri\n10. THT");
        System.out.print("Pilihan (angka) : ");
        int optDept = input.nextInt();
        String dept = "";
        String[] daftarDokter = {};
            switch (optDept) {
                case 1:
                    dept = "Toraks & Kardiovaskular";
                    daftarDokter = new String[]{"dr. Kento Yamazaki", "dr. Andika Pratama", "dr. Zayne Perkasa"};
                    break;
                case 2:
                    dept = "Neurologi";
                    daftarDokter = new String[]{"dr. Rio Gunawan", "dr. Inna Lutfiah Fatih"};
                    break;
                case 3:
                    dept = "Pulmonogi";
                    daftarDokter = new String[]{"dr. Asep Buhori", "dr. Alifian Revan"};
                    break;
                case 4:
                    dept = "Umum";
                    daftarDokter = new String[]{"dr. Amelia Rahman", "dr. Fina Nailatul", "dr. Sarah Nurfadillah", "dr. Hafizatul Rahmadania"};
                    break;
                case 5:
                    dept = "Bedah";
                    daftarDokter = new String[]{"dr. Theo Gunadi", "dr. Park Jisung", "dr. Muhammad Fathir"};
                    break;
                case 6:
                    dept = "Anak";
                    daftarDokter = new String[]{"dr. Gita Gutawa", "dr. Zaidan Dziaulfawwaz", "dr. Rakha Cibul"};
                case 7:
                    dept = "Kulit & Kelamin";
                    daftarDokter = new String[]{"dr. Kevin Geovani", "dr. Herlyana Fediani", "dr. Belvaria Hendriyani"};
                case 8:
                    dept = "Gigi";
                    daftarDokter = new String[]{"dr. Hendra Nandra", "dr. Waguri Waifu", "dr. Vania Winola"};  
                case 9:
                    dept = "Psikiatri";
                    daftarDokter = new String[]{"dr. Maya Cantika", "dr. Iman Paryudi", "dr. Rhys Meadows"};
                case 10:
                    dept = "THT";
                    daftarDokter = new String[]{"dr. Fatimah", "dr. Jihaan Hanifah"};
                default:
                    dept = "Umum";
                    daftarDokter = new String[]{"dr. Amelia Rahman"};
            }
        System.out.println("-----------------------------------------------------------");

        System.out.println("Pilih Dokter di Departemen " + dept + ": ");
        for (int i = 0; i < daftarDokter.length; i++) {
            System.out.println((i + 1) + ". " + daftarDokter[i]); }
        System.out.print("Pilihan (angka) : ");
        int optDoc = input.nextInt();
        String docName = (optDoc > 0 && optDoc <= daftarDokter.length) 
                         ? daftarDokter[optDoc - 1] : daftarDokter[0];
        System.out.println("-----------------------------------------------------------");

        System.out.println("Pilih Hari: ");
        System.out.println("1. Senin\n2. Selasa\n3. Rabu\n4. Kamis\n5. Jumat");
        System.out.print("Pilihan (angka) : ");
        int optHari = input.nextInt();
        String hari;
            switch (optHari) {
                case 1: hari = "Senin"; break;
                case 2: hari = "Selasa"; break;
                case 3: hari = "Rabu"; break;
                case 4: hari = "Kamis"; break;
                case 5: hari = "Jumat"; break;
                default: hari = "Senin"; }
        System.out.println("-----------------------------------------------------------");

        System.out.println("Pilih Jam: ");
        System.out.println("1. 08.00 - 09.00\n2. 10.00 - 11.00\n3. 13.00 - 14.00\n4. 16.00 - 17.00\n5. 19.00 - 20.00");
        System.out.print("Pilihan (angka) : ");
        int optJam = input.nextInt();
        String jam;
            switch (optJam) {
                case 1: jam = "08.00 - 09.00"; break;
                case 2: jam = "10.00 - 11.00"; break;
                case 3: jam = "13.00 - 14.00"; break;
                case 4: jam = "16.00 - 17.00"; break;
                case 5: jam = "19.00 - 20.00"; break;
                default: jam = "08.00 - 10.00"; }

        Pasien p = new Pasien(nama, id);
        Dokter d = new Dokter(docName, "DOC-" + dept.substring(0, 3).toUpperCase(), dept);
        Booking b = new Booking(p, d, hari, jam);

        System.out.println("___________________________________________________________");
        System.out.println("..................Ringkasan Data Pasien!...................");
        System.out.println(b.getBookingDetails());
        System.out.println("___________________________________________________________");
        System.out.println("=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=");
        
        input.close();
    }
}