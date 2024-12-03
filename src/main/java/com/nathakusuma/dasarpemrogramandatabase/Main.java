package com.nathakusuma.dasarpemrogramandatabase;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();
        Connection connection = db.getConnection();
        Repository repository = new Repository(connection);
        Service service = new Service(repository);
        Handler handler = new Handler(service);
        Scanner scanner = new Scanner(System.in);

        handler.run(scanner);

        try {
            connection.close();
            System.out.println("Koneksi dengan Microsoft SQL Server ditutup");
        } catch (Exception e) {
            System.out.println("Gagal menutup koneksi dengan Microsoft SQL Server: " + e.getMessage());
        }

        scanner.close();
    }
}
