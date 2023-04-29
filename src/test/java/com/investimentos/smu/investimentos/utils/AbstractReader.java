package com.investimentos.smu.investimentos.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class AbstractReader {
    public static String readJson(String resourceName) throws IOException {
        String json = String.join(" ",
                Files.readAllLines(
                        Paths.get("src","test","resources", "jsons", resourceName),
                        StandardCharsets.UTF_8)
        );
        return json;
    }
}