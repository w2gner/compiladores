package src;

import java.util.HashMap;
import java.util.Map;

public class gramatica {
    Map<String, Integer> gramatica = new HashMap<String, Integer>();

    public gramatica() {
        gramatica.put("Program", 1);
        gramatica.put("Label", 2);
        gramatica.put("Const", 3);
        gramatica.put("Var", 4);
        gramatica.put("Procedure", 5);
        gramatica.put("Begin", 6);
        gramatica.put("End", 7);
        gramatica.put("Integer", 8);
        gramatica.put("Array", 9);
        gramatica.put("Of", 10);
        gramatica.put("Call", 11);
        gramatica.put("Goto", 12);
        gramatica.put("If", 13);
        gramatica.put("Then", 14);
        gramatica.put("Else", 15);
        gramatica.put("While", 16);
        gramatica.put("Do", 17);
        gramatica.put("Repeat", 18);
        gramatica.put("Until", 19);
        gramatica.put("Readln", 20);
        gramatica.put("Writeln", 21);
        gramatica.put("Or", 22);
        gramatica.put("And", 23);
        gramatica.put("Not", 24);
        gramatica.put("Identificador", 25);
        gramatica.put("Inteiro", 26);
        gramatica.put("For", 27);
        gramatica.put("To", 28);
        gramatica.put("Case", 29);
        gramatica.put("+", 30);
        gramatica.put("-", 31);
        gramatica.put("*", 32);
        gramatica.put("/", 33);
        gramatica.put("[", 34);
        gramatica.put("]", 35);
        gramatica.put("(", 36);
        gramatica.put(")", 37);
        gramatica.put(":=", 38);
        gramatica.put(":", 39);
        gramatica.put("=", 40);
        gramatica.put(">", 41);
        gramatica.put(">=", 42);
        gramatica.put("<", 43);
        gramatica.put("<=", 44);
        gramatica.put("<>", 45);
        gramatica.put(",", 46);
        gramatica.put(";", 47);
        gramatica.put("literal", 48);
        gramatica.put(".", 49);
        gramatica.put("..", 50);
        gramatica.put("$", 51);
    }
}
