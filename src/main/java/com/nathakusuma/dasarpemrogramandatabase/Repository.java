package com.nathakusuma.dasarpemrogramandatabase;

import com.nathakusuma.dasarpemrogramandatabase.entity.Mahasiswa;
import com.nathakusuma.dasarpemrogramandatabase.entity.Nilai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Repository {
    private final Connection db;

    public Repository(Connection db) {
        this.db = db;
    }

    public void insertMahasiswa(String nim, String nama) throws SQLException {
        String query = """
                    INSERT INTO Mahasiswa (nim, nama) VALUES (?, ?)
                """;

        PreparedStatement statement = db.prepareStatement(query);
        statement.setString(1, nim);
        statement.setString(2, nama);
        statement.executeUpdate();
    }

    public void insertNilai(String nim, double nilai) throws SQLException {
        String query = """
                    INSERT INTO Nilai (nim, nilai) VALUES (?, ?)
                """;

        PreparedStatement statement = db.prepareStatement(query);
        statement.setString(1, nim);
        statement.setDouble(2, nilai);
        statement.executeUpdate();
    }

    public Map<Mahasiswa, Nilai> getAllMahasiswaNilai() throws SQLException {
        String query = """
                    SELECT m.nim,
                           m.nama,
                           n.nilai
                    FROM Mahasiswa m
                        JOIN Nilai n ON m.nim = n.nim
                    ORDER BY m.nim
                """;

        Map<Mahasiswa, Nilai> mahasiswaNilai = new HashMap<>();

        PreparedStatement statement = db.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String nim = resultSet.getString("nim");
            String nama = resultSet.getString("nama");
            double nilai = resultSet.getDouble("nilai");

            Mahasiswa mahasiswa = new Mahasiswa(nim, nama);
            Nilai nilaiObj = new Nilai(nim, nilai);

            mahasiswaNilai.put(mahasiswa, nilaiObj);
        }

        return mahasiswaNilai;
    }

    public void updateNilai(String nim, double nilai) throws SQLException {
        String query = """
                    UPDATE Nilai
                    SET nilai = ?
                    WHERE nim = ?
                """;

        PreparedStatement statement = db.prepareStatement(query);
        statement.setDouble(1, nilai);
        statement.setString(2, nim);
        statement.executeUpdate();
    }
}
