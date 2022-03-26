package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class FileHandler {
    public static void saveFile(String path, String str) throws FileNotFoundException, IOException {
        FileOutputStream arq = null;
        PrintStream ps = null;

        File f = new File(path);
        arq = new FileOutputStream(f);

        try {
            try {
                ps = new PrintStream(arq);
                ps.println(str);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Error while saving the file/n");
            e.printStackTrace();
        }
    }

    public static StringBuilder readFile(String path) throws FileNotFoundException, IOException {
        StringBuilder result = new StringBuilder();
        FileInputStream arq = null;

        try {
            File f = new File(path);
            arq = new FileInputStream(f);

            int readChar = arq.read();

            while (readChar != -1) {
                result.append((char) readChar);
                readChar = arq.read();
            }
        } catch (Exception e) {
            System.out.println("Error while reading the file/n");
            e.printStackTrace();
        } finally {
            if (arq != null) {
                arq.close();
            }
        }

        return result;
    }
}
