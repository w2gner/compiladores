package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Stack;

import obj.Token;

public class Compilador {
    public static void main(String[] args) {
        try {
            String path = "files/Program";
            testSavingFile(path);
            testReadingFile(path);
        } catch (Exception e) {
            System.out.println("Error while executing the program");
            e.printStackTrace();
        }
    }

    public static void testSavingFile(String path) throws FileNotFoundException, IOException {
        String program = "PROGRAM NOMEPROGRAMA ; "
                + "VAR "
                + "   X , Y , Z : INTEGER ; "
                + "BEGIN "
                + "   X := 10 ; "
                + "   Y := 20 ; "
                + "   Z := 30 ; "
                + "  REPEAT "
                + "    BEGIN "
                + "      WRITELN ( X , Y , Z ) ; "
                + "    END "
                + "  UNTIL X > 10 ; "
                + "END . ";

        io.FileHandler.saveFile(path, program);
    }

    public static void testReadingFile(String path) throws FileNotFoundException, IOException {
        StringBuilder texto = io.FileHandler.readFile(path);
        String programa = texto.toString();

        AnalisadorLexico analisadorLexico = new AnalisadorLexico();
        Stack<Token> pilha = analisadorLexico.buscaTokens(programa);
        pilha.forEach(System.out::println);
    }
}
