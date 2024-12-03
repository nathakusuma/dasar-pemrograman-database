package com.nathakusuma.dasarpemrogramandatabase;

import com.nathakusuma.dasarpemrogramandatabase.entity.Mahasiswa;
import com.nathakusuma.dasarpemrogramandatabase.entity.Nilai;

import java.sql.SQLException;
import java.util.Map;

public class Service {
    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public void insertMahasiswa(String nim, String nama) throws Exception {
        if (nim == null || nim.isBlank() || nama == null || nama.isBlank()) {
            throw new Exception("NIM dan nama harus diisi");
        }

        try {
            repository.insertMahasiswa(nim, nama);
        } catch (SQLException e) {
            if (e.getErrorCode() == 2627) {
                throw new Exception("NIM sudah ada");
            }
            throw new Exception("Gagal menyimpan data mahasiswa: " + e.getMessage());
        }
    }

    public void insertOrUpdateNilai(String nim, double nilai) throws Exception {
        if (nim == null || nim.isBlank()) {
            throw new Exception("NIM harus diisi");
        }

        try {
            repository.insertNilai(nim, nilai);
        } catch (SQLException e) {
            if (e.getErrorCode() == 547) {
                throw new Exception("NIM tidak ditemukan");
            } else if (e.getErrorCode() == 2627) {
                try {
                    repository.updateNilai(nim, nilai);
                    return;
                } catch (SQLException e2) {
                    throw new Exception("Gagal menyimpan data nilai: " + e2.getMessage());
                }
            }
            throw new Exception("Gagal menyimpan data nilai: " + e.getMessage());
        }
    }

    public Map<Mahasiswa, Nilai> getAllMahasiswaNilai() throws Exception {
        try {
            return repository.getAllMahasiswaNilai();
        } catch (SQLException e) {
            throw new Exception("Gagal mengambil data mahasiswa dan nilai: " + e.getMessage());
        }
    }
}
