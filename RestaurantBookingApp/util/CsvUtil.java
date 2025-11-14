package util;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CsvUtil {
    private static final String DATA_DIR = "data";

    static {
        try { Files.createDirectories(Paths.get(DATA_DIR)); }
        catch (IOException ignored) {}
    }

    public static void write(String filename, List<String[]> rows) {
        String path = Paths.get(DATA_DIR, filename).toString();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (String[] row : rows) {
                bw.write(String.join(",", escape(row)));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Lỗi ghi file: " + path);
            e.printStackTrace();
        }
    }

    public static List<String[]> read(String filename) {
        String path = Paths.get(DATA_DIR, filename).toString();
        List<String[]> rows = new ArrayList<>();
        if (!Files.exists(Paths.get(path))) return rows;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    rows.add(unescape(line.split(",", -1)));
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi đọc file: " + path);
        }
        return rows;
    }

    private static String[] escape(String[] fields) {
        for (int i = 0; i < fields.length; i++) {
            if (fields[i] == null) fields[i] = "";
            if (fields[i].contains(",") || fields[i].contains("\"") || fields[i].contains("\n")) {
                fields[i] = "\"" + fields[i].replace("\"", "\"\"") + "\"";
            }
        }
        return fields;
    }

    private static String[] unescape(String[] fields) {
        for (int i = 0; i < fields.length; i++) {
            String f = fields[i];
            if (f.startsWith("\"") && f.endsWith("\"")) {
                f = f.substring(1, f.length() - 1).replace("\"\"", "\"");
            }
            fields[i] = f.isEmpty() ? null : f;
        }
        return fields;
    }
}