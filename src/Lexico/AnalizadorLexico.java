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

import java.util.Stack;

import javax.swing.JOptionPane;

public class AnalizadorLexico 
{

    Token token = new Token();
    Stack<Token> lista = new Stack<Token>();
    //Lista lista = new Lista();
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
    
    public Stack<Token> getPalavra(String texto) 
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
                        continue;
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
                                while (String.valueOf(caracter).matches("[0-9]")) 
                                {

                                    caracter = texto.charAt(i);
                                    palavraToken += String.valueOf(caracter);
                                    i++;
                                    caracter = texto.charAt(i);

                                }
                                
                                if (String.valueOf(caracter).matches("[0-9],")) 
                                {
                                    JOptionPane.showMessageDialog(null, "Erro lexico o numero não deve ter ponto decimal\nLinha: " + contadorDelinha + "\nPalavra: " + palavraToken);
                                    erroLexico = true;
                                    break;   
                                }
                                
                                String palavra1 = palavraToken;

                                int numero = Integer.parseInt(palavra1);

                                if (FUN_tamanho_numero(numero)) 
                                {
                                    token.setCodigo(26);
                                    token.setlinha(String.valueOf(contadorDelinha));
                                    token.setNome(palavraToken);
                                    lista.push(token);
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
                                        token.setCodigo(48);
                                        token.setlinha(String.valueOf(contadorDelinha));
                                        token.setNome(palavraToken);
                                        lista.push(token);
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

                                                if (String.valueOf(caracter).matches(".")) 
                                                {
                                                    palavraToken += String.valueOf(caracter);
                                                    char a;
                                                    int j = i + 1;
                                                    System.out.println(palavraToken);
                                                    
                                                    if( i +1 == texto.length())
                                                    {
                                                        // condição para nao acessar o array char vazio 
                                                    }
                                                    else
                                                    {
                                                        a = texto.charAt(j);
                                                       
                                                        if (String.valueOf(a).matches("[.]")) 
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
        catch (StringIndexOutOfBoundsException s) 
        {
            System.out.println("Array Vazio.");
        }
        catch(Exception e)
        {
            erroLexico = true;
            setLista(palavraToken, contadorDelinha);
            erro(contadorDelinha, palavraToken);
        }

        //setLista("$", contadorDelinha);
        edtComp.getListaMunuAtt(lista);        
        return lista;

    }

    public void setLista(String palavra, int linha) 
    {

        Token token = new Token();
        Integer codigo = getCodigoToken(palavra);

        token.setCodigo(codigo);
        token.setNome(palavra);
        token.setlinha(String.valueOf(linha));
        
        //System.out.println("token: " + token + " -" + " codigo: " + codigo);
        lista.push(token);
        
    }

    private Integer getCodigoToken(String token) {

        Integer codigoToken = Gramatica.Gramatica.TERMINAIS_E_NAO_TERMINAIS.get(token.toUpperCase());
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
            return Gramatica.Gramatica.TERMINAIS_E_NAO_TERMINAIS.get("IDENTIFICADOR");
        }
        return Gramatica.Gramatica.TERMINAIS_E_NAO_TERMINAIS.get("INTEIRO");
    }
}
