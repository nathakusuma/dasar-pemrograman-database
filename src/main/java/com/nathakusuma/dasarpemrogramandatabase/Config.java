package com.nathakusuma.dasarpemrogramandatabase;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private static Dotenv dotenv;

    public static String getEnv(String key) {
        if (dotenv == null) {
            dotenv = Dotenv.load();
        }

        return dotenv.get(key);
    }
}
