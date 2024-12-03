package com.nathakusuma.dasarpemrogramandatabase;

import com.nathakusuma.dasarpemrogramandatabase.entity.Mahasiswa;
import com.nathakusuma.dasarpemrogramandatabase.entity.Nilai;

import java.util.Map;
import java.util.Scanner;

public class Handler {
    private final Service service;

    public Handler(Service service) {
        this.service = service;
    }

    public void insertMahasiswa(Scanner scanner) {
        System.out.print("NIM: ");
        String nim = scanner.next();
        scanner.nextLine();
        System.out.print("Nama: ");
        String nama = scanner.nextLine();

        try {
            service.insertMahasiswa(nim, nama);
            System.out.println("Data mahasiswa berhasil disimpan");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertNilai(Scanner scanner) {
        System.out.print("NIM: ");
        String nim = scanner.next();
        System.out.print("Nilai: ");
        double nilai = scanner.nextDouble();

        try {
            service.insertOrUpdateNilai(nim, nilai);
            System.out.println("Data nilai berhasil disimpan");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllMahasiswaNilai() {
        try {
            Map<Mahasiswa, Nilai> mahasiswaNilai = service.getAllMahasiswaNilai();

            System.out.printf("+-----------------+---------------------------+------------+%n");
            System.out.printf("| %-15s | %-25s | %-10s |%n", "NIM", "Nama", "Nilai");
            System.out.printf("+-----------------+---------------------------+------------+%n");

            mahasiswaNilai.forEach((mahasiswa, nilai) ->
                    System.out.printf("| %-15s | %-25s | %10.2f |%n",
                            mahasiswa.getNim(),
                            mahasiswa.getNama(),
                            nilai.getNilai()));

            System.out.printf("+-----------------+---------------------------+------------+%n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void run(Scanner scanner) {
        infiniteLoop:
        while (true) {
            System.out.println();
            System.out.println("Menu:");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Tambah/Ubah Nilai");
            System.out.println("3. Tampilkan Semua Mahasiswa dan Nilai");
            System.out.println("0. Keluar");
            System.out.print("Pilihan: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (pilihan) {
                case 1 -> insertMahasiswa(scanner);
                case 2 -> insertNilai(scanner);
                case 3 -> getAllMahasiswaNilai();
                case 0 -> {
                    System.out.println("Program selesai");
                    break infiniteLoop;
                }
                default -> System.out.println("Pilihan tidak valid");
            }
        }
    }
}
