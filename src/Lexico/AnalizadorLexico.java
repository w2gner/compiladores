/**
 *
 * @author Felipe Costa de jesus
 * @author Pedro Ventura
 * @author 
 * 
 */
package Lexico;

import Models.Lista;
import Models.Token;
import Tela.EditorCompilador;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class AnalizadorLexico 
{

    Token token = new Token();
    Lista listaToken = new Lista();
    EditorCompilador edtComp = new EditorCompilador();
    public boolean erroLexico = false;                  // Variavel para enviar ao editor de tela para mensagem de sucesso ou erro no console


    /*
     * Verificar o tamanho dos caracteres informado do texto
     */
    public static boolean FUN_tamanho_caracter(String palavraToken) 
    {

        if (palavraToken.length() <= 30) 
        {
            return true;
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "O Idenficador ultrapassou o limite permitido de 30 caracteres","",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /*
     * Verificar o tamanho do numero inteiro informado
     */
    public static boolean FUN_tamanho_numero(int numero) 
    {

        if (numero > -32767 && numero < 32767) 
        {
            return true;
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "O numero passou do limite\nValor permitido: numero maior que -32767 e menor que 32767 ","",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    

    /*
     * Função para mostrar tela de erro com a linha e palavra
     */
    public void erro(int contadorDelinha, String caracter) 
    {
        JOptionPane.showMessageDialog(null, "Erro lexico houve uma interrupção erro na linha : " + contadorDelinha + "\nPalavra: " + caracter,"",JOptionPane.ERROR_MESSAGE); 
        erroLexico = true;
    }
    
    public Lista getPalavra(String texto) 
    {
        
        int contadorDelinha = 1;// Esta variavel conta as linhas do texto
        String palavraToken = "";
        char caracter;

        try 
        {
            for (int i = 0; i < texto.length(); i++) 
            {
                Token token = new Token();
                caracter = texto.charAt(i);
                
                // Verificação de contagem de linha 
                if (String.valueOf(caracter).matches("[\n]")) 
                {
                    contadorDelinha++;
                } 
                else 
                {
                    // Verificação de espaços e tab
                    if (String.valueOf(caracter).matches(" ")||String.valueOf(caracter).matches("\t")) 
                    {

                    } 
                    else 
                    {
                        
                        // Verificação de comandos nativos e palavras de variaveis 
                        if (String.valueOf(caracter).matches("[a-zA-Z_]")) 
                        {

                            while (String.valueOf(caracter).matches("[a-zA-Z0-9_]")) 
                            {

                                caracter = texto.charAt(i);
                                palavraToken += String.valueOf(caracter);

                                i++;
                                caracter = texto.charAt(i);

                            }

                            setLista(palavraToken, contadorDelinha);

                            palavraToken = "";
                            i--;

                        } 
                        else 
                        {
                            // Verificação de inteiros 
                            if (String.valueOf(caracter).matches("[0-9]")) 
                            {
                                boolean num = false;
                                
                                while (String.valueOf(caracter).matches("[-0-9a-zA-Z_.,]")) 
                                {

                                    caracter = texto.charAt(i);
                                    palavraToken += String.valueOf(caracter);
                                    i++;
                                    caracter = texto.charAt(i);

                                }
                                
                                if (String.valueOf(caracter).matches("[0-9.,]"))  
                                {
                                    JOptionPane.showMessageDialog(null, "Erro lexico o numero não deve ter ponto decimal\nLinha: " + contadorDelinha + "\nPalavra: " + palavraToken);
                                    erroLexico = true;
                                    break;   
                                }
                                
                                String palavra1 = palavraToken;

                                int numero = Integer.parseInt(palavra1);

                                if (FUN_tamanho_numero(numero)) 
                                {
                                    token.setCodigo("26");
                                    token.setlinha(String.valueOf(contadorDelinha));
                                    token.setNome(palavraToken);
                                    listaToken.adicionar(token);
                                    palavraToken = "";
                                    i--;
                                }
                                else
                                {
                                   palavraToken = "";
                                   contadorDelinha++;
                                   erroLexico = true;
                                }
                                   
                            }
                            else 
                            {
                                // Verificação de definição de literal ' Texto'
                                if (String.valueOf(caracter).matches("[']")) 
                                {
                                    int j = i + 1;
                                    int limitChar = 0;
                                    char a = texto.charAt(j);
                                    palavraToken += String.valueOf(caracter);
                                    
                                    while (!String.valueOf(a).matches("[']")) 
                                    {
                                        a = texto.charAt(j);
                                        palavraToken += String.valueOf(a);
                                        j++;
                                        limitChar++;
                                    }
                                    
                                    j--;
                                    i = j;
                                    if(limitChar > 255)
                                    {
                                        JOptionPane.showMessageDialog(null, "Sequencia de caracteres literais exedeu o limite de 255\nQuantidade de Caracteres: " + limitChar ,"",JOptionPane.ERROR_MESSAGE);
                                        erroLexico = true;
                                    }
                                    else
                                    {
                                        token.setCodigo("48");
                                        token.setlinha(String.valueOf(contadorDelinha));
                                        token.setNome(palavraToken);
                                        listaToken.adicionar(token);
                                        palavraToken = "";
                                    }

                                } 
                                else 
                                {
                                    // Verificação de operadores 
                                    if (String.valueOf(caracter).matches("<")) 
                                    {
                                        palavraToken += String.valueOf(caracter);
                                        char a;
                                        int j = i + 1;
                                        a = texto.charAt(j);
                                        
                                        if (String.valueOf(a).matches("[=]")) 
                                        {
                                            palavraToken += String.valueOf(a);
                                            i = j;
                                        } 
                                        else 
                                        {
                                            if (String.valueOf(a).matches("[>]")) 
                                            {
                                                palavraToken += String.valueOf(a);
                                                i = j;
                                            }
                                        }

                                        setLista(palavraToken, contadorDelinha);
                                        palavraToken = "";
                                        
                                    } 
                                    else 
                                    {
                                        if (String.valueOf(caracter).matches(">")) 
                                        {
                                            palavraToken += String.valueOf(caracter);
                                            char a;
                                            int j = i + 1;
                                            a = texto.charAt(j);
                                            
                                            if (String.valueOf(a).matches("[=]")) 
                                            {
                                                palavraToken += String.valueOf(a);

                                                i = j;
                                            }
                                            
                                            setLista(palavraToken, contadorDelinha);
                                            palavraToken = "";

                                        } 
                                        else 
                                        {
                                            if (String.valueOf(caracter).matches(":")) 
                                            {
                                                palavraToken += String.valueOf(caracter);
                                                char a;
                                                int j = i + 1;
                                                a = texto.charAt(j);

                                                if (String.valueOf(a).matches("[=]")) 
                                                {
                                                    palavraToken += String.valueOf(a);
                                                    i = j;
                                                }
                                                else 
                                                {
                                                    if (String.valueOf(a).matches("[:]")) 
                                                    {
                                                        palavraToken += String.valueOf(a);

                                                        i = j;
                                                    }
                                                }
                                                
                                                setLista(palavraToken, contadorDelinha);
                                                palavraToken = "";

                                            } 
                                            else 
                                            {

                                                if (String.valueOf(caracter).matches("[\\[\\]=+;,*)(-/].")) 
                                                {
                                                    palavraToken += String.valueOf(caracter);
                                                    setLista(palavraToken, contadorDelinha);
                                                    palavraToken = "";

                                                } 
                                                else 
                                                {

                                                    // Verificação de comentario de linha
                                                    // comentario de linha sera representado por os seguintes simbolos (*  *)
                                                    if (String.valueOf(caracter).matches("[(]")) 
                                                    {
                                                        //contadorDelinha++;
                                                        char a;
                                                        int j = i + 1;
                                                        a = texto.charAt(j);
                                                        
                                                        if (String.valueOf(a).matches("[*]")) 
                                                        {
                                                            a = texto.charAt(j+1);
                                                            if (!String.valueOf(a).matches("[*]")) 
                                                            {
                                                                if (!String.valueOf(a).matches("[)]"))
                                                                {         
                                                                    while (!String.valueOf(a).matches("[)]")) 
                                                                    {                                                                            
                                                                        a = texto.charAt(j);
                                                                        j++;
                                                                        i = j;
                                                                        i--;
                                                                    }
                                                                }
                                                            } 
                                                        }
                                                    } 
                                                    else if (String.valueOf(caracter).matches(".")) 
                                                    {
                                                        palavraToken += String.valueOf(caracter);
                                                        char a;
                                                        int j = i + 1;
                                                        a = texto.charAt(j);
                                                        
                                                        if (String.valueOf(a).matches("[.]")) 
                                                        {
                                                            palavraToken += String.valueOf(a);

                                                            i = j;
                                                        }
                                                        
                                                        setLista(palavraToken, contadorDelinha);
                                                        palavraToken = "";

                                                    } 
                                                    else 
                                                    {
                                                        // Caso não entre em nenhuma verificação acima é um caracter invalido 
                                                        JOptionPane.showMessageDialog(null, "Erro lexico houve uma interrupção erro na linha : " + contadorDelinha + "\nPalavra: " + caracter);
                                                        erroLexico = true;
                                                        break;
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
            }
            if(erroLexico == false)
                JOptionPane.showMessageDialog(null, "Programa compilado com sucesso");
            
        } 
        catch (Exception e) 
        {
            erroLexico = true;
            setLista(palavraToken, contadorDelinha);
            erro(contadorDelinha, palavraToken);
            
        }

        //setLista("$", contadorDelinha);
        edtComp.getListaMunuAtt(listaToken);        
        return listaToken;

    }

    // Alterar para uma classe GRAMATICA e implementar MAP para criar a tabela com os códigos
    public void setLista(String palavra, int linha) 
    {

        Token token = new Token();
        
//        Pattern p = Pattern.compile("[a-z][0-9a-z]{0,29}$", Pattern.CASE_INSENSITIVE);
//        boolean v = p.matcher(palavra).matches();
//        if(v){
//            System.out.println("PATTERN: " + v);
//        }
        Integer codigo = getCodigoToken(palavra);

        token.setCodigo(codigo.toString());
        token.setNome(palavra);
        token.setlinha(String.valueOf(linha));
        
//        System.out.println("token: " + token + " -" + " codigo: " + codigo);

////        switch (palavra.toUpperCase()) 
////        {
////            case "PROGRAM":
////                token.setCodigo("1");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////
////            case "LABEL":
////                token.setCodigo("2");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////
////            case "CONST":
////                token.setCodigo("3");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////
////            case "VAR":
////                token.setCodigo("4");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////
////            case "PROCEDURE":
////                token.setCodigo("5");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////
////            case "BEGIN":
////                token.setCodigo("6");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////
////            case "END":
////                token.setCodigo("7");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////
////            case "INTEGER":
////                token.setCodigo("8");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "ARRAY":
////                token.setCodigo("9");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////
////            case "OF":
////                token.setCodigo("10");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////
////            case "CALL":
////                token.setCodigo("11");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "GOTO":
////                token.setCodigo("12");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "IF":
////                token.setCodigo("13");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "THEN":
////                token.setCodigo("14");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "ELSE":
////                token.setCodigo("15");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "WHILE":
////                token.setCodigo("16");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "DO":
////                token.setCodigo("17");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "REPEAT":
////                token.setCodigo("18");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "UNTIL":
////                token.setCodigo("19");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "READLN":
////                token.setCodigo("20");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "WRITELN":
////                token.setCodigo("21");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "OR":
////                token.setCodigo("22");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "AND":
////                token.setCodigo("23");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "NOT":
////                token.setCodigo("24");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////
////            case "INTEIRO":
////                token.setCodigo("26");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "FOR":
////                token.setCodigo("27");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "TO":
////                token.setCodigo("28");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "CASE":
////                token.setCodigo("29");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "+":
////                token.setCodigo("30");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "-":   
////                token.setCodigo("31");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "*":
////                token.setCodigo("32");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "/":
////                token.setCodigo("33");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "[":
////                token.setCodigo("34");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "]":
////                token.setCodigo("35");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "(":
////                token.setCodigo("36");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case ")":
////                token.setCodigo("37");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case ":=":
////                token.setCodigo("38");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case ":":
////                token.setCodigo("39");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "=":
////                token.setCodigo("40");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case ">":
////                token.setCodigo("41");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case ">=":
////                token.setCodigo("42");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "<":
////                token.setCodigo("43");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "<=":
////                token.setCodigo("44");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "<>":
////                token.setCodigo("45");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case ",":
////                token.setCodigo("46");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case ";":
////                token.setCodigo("47");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "LITERAL":
////                token.setCodigo("48");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case ".":
////                token.setCodigo("49");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "..":
////                token.setCodigo("50");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////            case "$":
////                token.setCodigo("51");
////                token.setNome(palavra);
////                token.setlinha(String.valueOf(linha));
////                break;
////
////            default:
////                
////                if (FUN_tamanho_caracter(palavra)) {
////                    token.setCodigo("25");
////                    token.setNome(palavra);
////                    token.setlinha(String.valueOf(linha));
////                }
////                else
////                    erroLexico = true;
////                break;
//
//        }

        listaToken.adicionar(token);
        
    }

    private Integer getCodigoToken(String token) {

        Integer codigoToken = gramatica.Gramatica.TERMINAIS_E_NAO_TERMINAIS.get(token);
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
            return gramatica.Gramatica.TERMINAIS_E_NAO_TERMINAIS.get("IDENTIFICADOR");
        }
        return gramatica.Gramatica.TERMINAIS_E_NAO_TERMINAIS.get("INTEIRO");
    }
}
