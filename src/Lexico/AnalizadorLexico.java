/**
 *
 * @author Felipe Costa de jesus
 * @author Pedro Ventura
 * @author Wagner
 *
 */
package Lexico;

import Models.Token;
import Tela.EditorCompilador;
import java.util.List;

import java.util.Stack;

import javax.swing.JOptionPane;

import Gramatica.Gramatica;

public class AnalizadorLexico {

    Token token = new Token();
    Stack<Token> lista = new Stack<Token>();
    // Lista lista = new Lista();
    EditorCompilador edtComp = new EditorCompilador();
    public boolean erroLexico = false; // Variavel para enviar ao editor de tela para mensagem de sucesso ou erro no
                                       // console

    /*
     * Verificar o tamanho dos caracteres informado do texto
     */
    public static boolean FUN_tamanho_caracter(String palavraToken) {

        if (palavraToken.length() <= 30) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, " O Idenficador ultrapassou o limite permitido de 30 caracteres!",
                    "",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /*
     * Verificar o tamanho do numero inteiro informado
     */
    public static boolean FUN_tamanho_numero(int numero) {

        if (numero > -32767 && numero < 32767) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null,
                    "O numero passou do limite\nValor permitido: numero maior que -32767 e menor que 32767 ", "",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /*
     * Função para mostrar tela de erro com a linha e palavra
     */
    public void erro(int contadorDelinha, String caracter) {
        JOptionPane.showMessageDialog(null,
                "Erro lexico houve uma interrupção erro na linha : " + contadorDelinha + "\nPalavra: " + caracter, "",
                JOptionPane.ERROR_MESSAGE);
        erroLexico = true;
    }

    public Stack<Token> getPalavra1(String texto) {

        int contadorDelinha = 1;// Esta variavel conta as linhas do texto
        String palavraToken = "";
        char caracter;

        try {
            for (int i = 0; i < texto.length(); i++) {
                Token token = new Token();
                caracter = texto.charAt(i);

                // Verificação de contagem de linha
                if (String.valueOf(caracter).matches("[\n]")) {
                    contadorDelinha++;
                } else {
                    // Verificação de espaços e tab
                    if (String.valueOf(caracter).matches(" ") || String.valueOf(caracter).matches("\t")) {
                        continue;
                    }

                    // TODO AJUSTAR PROXIMO CARACTER
                    // Verificação de comentario de linha
                    // comentario de linha sera representado por os seguintes simbolos (* *)
                    if (String.valueOf(caracter).matches("[(]")) {
                        contadorDelinha++;
                        char a;
                        int j = i + 1;
                        a = texto.charAt(j);

                        // System.out.println(caracter);
                        // System.out.println(a);
                        if (String.valueOf(a).matches("[*]")) {
                            a = texto.charAt(j + 1);
                            // System.out.println(a);
                            if (!String.valueOf(a).matches("[*]")) {
                                if (!String.valueOf(a).matches("[)]")) {
                                    while (!String.valueOf(a).matches("[)]")) {
                                        a = texto.charAt(j);
                                        // System.out.println(a);
                                        j++;
                                        i = j;
                                        i--;
                                    }
                                }
                            }
                        }
                    } else {

                        // Verificação de comandos nativos e palavras de variaveis
                        if (String.valueOf(caracter).matches("[a-zA-Z_]")) {

                            while (String.valueOf(caracter).matches("[a-zA-Z0-9_]")) {

                                caracter = texto.charAt(i);
                                palavraToken += String.valueOf(caracter);

                                i++;
                                caracter = texto.charAt(i);

                            }

                            setLista(palavraToken, contadorDelinha);

                            palavraToken = "";
                            i--;

                        } else {
                            // TODO VERIFICAR REGEX NUMERO NEGATIVO
                            // Verificação de inteiros
                            if (String.valueOf(caracter).matches("[0-9]")) {
                                while (String.valueOf(caracter).matches("[0-9]")) {

                                    caracter = texto.charAt(i);
                                    palavraToken += String.valueOf(caracter);
                                    i++;
                                    caracter = texto.charAt(i);

                                }

                                if (String.valueOf(caracter).matches("[0-9],")) {
                                    // JOptionPane.showMessageDialog(null,
                                    // "Erro lexico o numero não deve ter ponto decimal\nLinha: " + contadorDelinha
                                    // + "\nPalavra: " + palavraToken);
                                    erro(contadorDelinha, palavraToken);
                                    break;
                                }

                                String palavra1 = palavraToken;

                                int numero = Integer.parseInt(palavra1);

                                if (FUN_tamanho_numero(numero)) {
                                    token.setCodigo(26);
                                    token.setlinha((contadorDelinha));
                                    token.setNome(palavraToken);
                                    lista.push(token);
                                    palavraToken = "";
                                    i--;
                                } else {
                                    palavraToken = "";
                                    contadorDelinha++;
                                    erroLexico = true;
                                }

                            } else {
                                // Verificação de definição de literal ' Texto'
                                if (String.valueOf(caracter).matches("[']")) {
                                    int j = i + 1;
                                    int limitChar = 0;
                                    char a = texto.charAt(j);
                                    palavraToken += String.valueOf(caracter);

                                    while (!String.valueOf(a).matches("[']")) {
                                        a = texto.charAt(j);
                                        palavraToken += String.valueOf(a);
                                        j++;
                                        limitChar++;
                                    }

                                    j--;
                                    i = j;
                                    if (FUN_tamanho_caracter(palavraToken)) {
                                        // JOptionPane.showMessageDialog(null,
                                        // "Sequencia de caracteres literais exedeu o limite de 255\nQuantidade de
                                        // Caracteres: "
                                        // + limitChar,
                                        // "", JOptionPane.ERROR_MESSAGE);
                                        erroLexico = true;
                                    } else {
                                        token.setCodigo(48);
                                        token.setlinha((contadorDelinha));
                                        token.setNome(palavraToken);
                                        lista.push(token);
                                        palavraToken = "";
                                    }

                                } else {
                                    // Verificação de operadores
                                    if (String.valueOf(caracter).matches("<")) {
                                        palavraToken += String.valueOf(caracter);
                                        char a;
                                        int j = i + 1;
                                        a = texto.charAt(j);

                                        if (String.valueOf(a).matches("[=]")) {
                                            palavraToken += String.valueOf(a);
                                            i = j;
                                        } else {
                                            if (String.valueOf(a).matches("[>]")) {
                                                palavraToken += String.valueOf(a);
                                                i = j;
                                            }
                                        }

                                        setLista(palavraToken, contadorDelinha);
                                        palavraToken = "";

                                    } else {
                                        if (String.valueOf(caracter).matches(">")) {
                                            palavraToken += String.valueOf(caracter);
                                            char a;
                                            int j = i + 1;
                                            a = texto.charAt(j);

                                            if (String.valueOf(a).matches("[=]")) {
                                                palavraToken += String.valueOf(a);

                                                i = j;
                                            }

                                            setLista(palavraToken, contadorDelinha);
                                            palavraToken = "";

                                        } else {
                                            if (String.valueOf(caracter).matches(":")) {
                                                palavraToken += String.valueOf(caracter);
                                                char a;
                                                int j = i + 1;
                                                a = texto.charAt(j);

                                                if (String.valueOf(a).matches("[=]")) {
                                                    palavraToken += String.valueOf(a);
                                                    i = j;
                                                } else {
                                                    if (String.valueOf(a).matches("[:]")) {
                                                        palavraToken += String.valueOf(a);

                                                        i = j;
                                                    }
                                                }

                                                setLista(palavraToken, contadorDelinha);
                                                palavraToken = "";

                                            } else {

                                                if (String.valueOf(caracter).matches(".")) {
                                                    palavraToken += String.valueOf(caracter);
                                                    char a;
                                                    int j = i + 1;
                                                    // System.out.println(palavraToken);

                                                    if (i + 1 == texto.length()) {
                                                        // condição para nao acessar o array char vazio
                                                    } else {
                                                        a = texto.charAt(j);

                                                        if (String.valueOf(a).matches("[.]")) {
                                                            palavraToken += String.valueOf(a);
                                                            i = j;
                                                        }
                                                    }
                                                    setLista(palavraToken, contadorDelinha);
                                                    palavraToken = "";

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (erroLexico == false) {
                JOptionPane.showMessageDialog(null, "Programa compilado com sucesso");
            }

        } catch (StringIndexOutOfBoundsException s) {
            System.out.println("Array Vazio.");
        } catch (Exception e) {
            erroLexico = true;
            setLista(palavraToken, contadorDelinha);
            erro(contadorDelinha, palavraToken);
        }

        // setLista("$", contadorDelinha);
        edtComp.getListaMunuAtt(lista);
        return lista;

    }

    public void setLista(String palavra, int linha) {

        Token token = new Token();
        Integer codigo = getCodigoToken(palavra);

        token.setCodigo(codigo);
        token.setNome(palavra);
        token.setlinha(linha);

        // System.out.println("token: " + token + " -" + " codigo: " + codigo);
        lista.push(token);

    }

    private Integer getCodigoToken(String token) {

        Integer codigoToken = Gramatica.TERMINAIS_E_NAO_TERMINAIS.get(token.toUpperCase());
        if (codigoToken == null) {
            // ou é um INTEGER ou pe um IDENTIFICADOR
            return getIdentificadorOuInteiro(token);
        }
        return codigoToken;
    }

    private Integer getIdentificadorOuInteiro(String token) {
        // verifica se é um identificador ou inteiro
        char[] cList = token.toCharArray();
        boolean identificador = true;
        for (char c : cList) {
            if (Character.getType(c) != Character.UPPERCASE_LETTER) {
                identificador = true;
            }
        }

        if (identificador) {
            return Gramatica.TERMINAIS_E_NAO_TERMINAIS.get("IDENTIFICADOR");
        }
        return Gramatica.TERMINAIS_E_NAO_TERMINAIS.get("INTEIRO");
    }

    public Stack<Token> getPalavra(List<String> linhas) {
        Stack<Token> tokens = new Stack<Token>();

        Gramatica gramatica = new Gramatica();

        List<Token> gTokens = gramatica.getTokens();

        Boolean comentarios = false;

        for (Integer linha = 1; linha <= linhas.size(); linha++) {
            String l = linhas.get(linha - 1);

            String palavra = "";
            for (Integer i = 0; i < l.length(); i++) {
                Character caracter = l.charAt(i);
                // comentarios
                if (caracter == '(' && i < l.length() + 1 && l.charAt(i + 1) == '*') {
                    if (palavra != "") {
                        erro(linha, "Erro, inicio de comentario com empilhagem no palavra.");
                        // System.out.println("Erro, inicio de comentario com empilhagem no palavra.");
                        break;
                    }
                    comentarios = true;
                } else if (caracter == ')' && 1 < l.length() && l.charAt(i - 1) == '*') {
                    comentarios = false;
                } else if (comentarios) {
                    continue;
                }
                // Logica Literal
                else if (caracter == '\'') {
                    palavra = "";
                    Integer j = i + 1;
                    if (j < l.length()) {
                        caracter = l.charAt(j);

                        while (caracter != '\'') {

                            palavra += caracter.toString();
                            j++;
                            if (j == l.length()) {
                                erro(linha, "Literal não foi fechado na linha de abertura.");
                                // JOptionPane.showMessageDialog(null,
                                // "Lexico" + linha + "Literal não foi fechado na linha de abertura.");
                                // throw new EditorException("Lexico", linha, "Literal não foi fechado na linha
                                // de abertura.");
                                break;
                            }
                            caracter = l.charAt(j);

                        }
                    } else {
                        erro(linha, "Literal não foi fechado na linha de abertura.");
                        break;
                    }

                    if (palavra.length() > 255) {
                        erro(linha, "Literal com tamanho maior que 255 caracteres.");
                        erroLexico = true;
                        break;
                    }

                    Token auxToken = geradorToken("literal", palavra, gTokens, linha);
                    if (auxToken != null) {
                        tokens.add(auxToken);
                        palavra = "";
                    }
                    i = j;
                    continue;
                }
                // Logica Identificadores OU Palavra Reservada
                else if ((caracter >= 'a' && caracter <= 'z') || (caracter >= 'A' && caracter <= 'Z')) {

                    Integer j = i;
                    palavra = "";

                    while ((caracter >= 'a' && caracter <= 'z')
                            || (caracter >= 'A' && caracter <= 'Z')
                            || (caracter >= '0' && caracter <= '9')
                            || (caracter == '_')) {

                        palavra += caracter.toString();
                        j++;
                        if (j == l.length()) {
                            break;
                        }
                        caracter = l.charAt(j);

                    }
                    if (palavra.length() > 30) {
                        // System.out.println("erro linha: " + linha);
                        erro(linha, "Identificador com tamanho maior que 30 caracteres.");
                        break;
                    }
                    Token auxToken = geradorToken("identificador", palavra, gTokens, linha);
                    if (auxToken != null) {
                        tokens.add(auxToken);
                        palavra = "";
                    }

                    palavra = "";
                    i = j - 1;
                    continue;
                }
                // Inteiro
                else if ((caracter >= '0' && caracter <= '9') ||
                        (caracter == '-' && i + 1 < l.length() &&
                                (l.charAt(i + 1) >= '0' && l.charAt(i + 1) <= '9'))) {
                    Integer j = i;
                    palavra = "";
                    if (caracter == '-') {
                        palavra += caracter.toString();
                        j++;

                    }
                    if (j < l.length() - 1) {
                        caracter = l.charAt(j);
                    }
                    while (caracter >= '0' && caracter <= '9') {

                        palavra += caracter.toString();
                        j++;
                        if (j == l.length()) {
                            break;
                        }
                        caracter = l.charAt(j);

                    }

                    Token auxToken = geradorToken("inteiro", palavra, gTokens, linha);

                    Integer local_int = Integer.parseInt(palavra);
                    if (local_int > 32767 || local_int < -32767) {
                        erro(linha, "Inteiro com tamanho incorreto.");
                        break;
                    }

                    if (auxToken != null) {
                        tokens.add(auxToken);
                        palavra = "";
                    }

                    palavra = "";
                    i = j - 1;
                    continue;
                }
                // Outros Simbolos
                else if (caracter != ' ' && caracter != '\t') {

                    palavra += caracter.toString();

                    Token auxToken = geradorToken(null, palavra, gTokens, linha);

                    if (auxToken != null) {

                        // Verifica se esse acumulado mais o próximo elemento tambem é um token
                        if (l.length() > i + 1) {
                            palavra += l.charAt(i + 1);
                            Token auxToken2 = geradorToken("", palavra, gTokens, linha);
                            if (auxToken2 != null) {
                                tokens.add(auxToken2);
                                i++;
                            } else {
                                tokens.add(auxToken);
                            }
                        } else {
                            tokens.add(auxToken);
                        }

                        palavra = "";
                    }

                }

            }

        }
        if (comentarios) {
            // throw new EditorException("Lexico", linha.size(), "Comentario não foram
            // fechados.");
            erro(linhas.size(), "Comentario não foram fechados.");

        }
        return tokens;
    }

    private Token geradorToken(String tipo, String palavra, List<Token> tokens, Integer linha) {
        try {
            if (palavra == null || palavra == "") {
                System.out.println("NUll?");
                return null;
            }

            if (tipo == "literal") {
                return new Token(48, palavra, linha);
            }
            if (tipo == "inteiro") {
                return new Token(26, palavra, linha);
            }

            for (Token token : tokens) {
                if ((palavra.toUpperCase()).equals((token.getNome()).toUpperCase())) {
                    return new Token(token.getCodigo(), palavra, linha);
                }
            }

            if (tipo == "identificador") {
                return new Token(25, palavra, linha);
            }

        } catch (Exception e) {
        }
        return null;
    }
}
