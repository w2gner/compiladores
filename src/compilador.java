package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Stack;

public class compilador {
    public static void main(String[] args) {
        try {
            String path = "files/texto";
            testSavingFile(path);
            testReadingFile(path);
        } catch (Exception e) {
            System.out.println("Error while executing the program");
            e.printStackTrace();
        }
    }

    public static void testSavingFile(String path) throws FileNotFoundException, IOException {
        String texto = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.\n"
                + "Nam pretium luctus augue, et accumsan libero lobortis vitae. Aliquam non elit id diam dictum ullamcorper. Pellentesque at mi non odio iaculis elementum nec sit amet velit. Donec luctus turpis tempor lectus luctus, id posuere erat tincidunt. Pellentesque quis dictum augue. Donec vitae tellus non sapien efficitur placerat in at est. Aliquam diam odio, rhoncus vitae consectetur cursus, sollicitudin sed lacus.\n"
                + "1.025 569 1 2\n"
                + "Cras dignissim erat in imperdiet imperdiet. Donec eget ante urna. Nulla vel lectus hendrerit est placerat sollicitudin. Integer porttitor velit sed mattis dictum. Vivamus non mi id purus euismod consectetur. Suspendisse sed.";

        io.fileHandler.saveFile(path, texto);
    }

    public static void testReadingFile(String path) throws FileNotFoundException, IOException {
        StringBuilder texto = io.fileHandler.readFile(path);
        String meuTexto = texto.toString();
        Stack minhaPilha = new Stack();

        // System.out.println(texto.toString());

        // for (int i = 0; i < meuTexto.length(); i++) {
        // minhaPilha.add(meuTexto.split(" "));
        // }
    }
}
