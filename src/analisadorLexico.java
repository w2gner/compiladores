package src;

import java.util.Stack;
import obj.Token;

/**
 * Faz a conversão do programa fonte em tokens (palavras da gramática) que devem
 * ser utilizadas para verificar se estã dentro da sintaxe
 */
public class AnalisadorLexico {
    Integer currentLine;
    Character nextChar;

    public Stack<Token> buscaTokens(String programa) {
        String[] tokens = programa.split(" ");
        Stack<Token> pilhaTokens = new Stack<>();

        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            if (token.trim().length() == 0) {
                continue;
            }
            Integer code = getCodigoToken(token);
            pilhaTokens.push(new Token(token, code, currentLine));
        }
        return pilhaTokens;
    }

    private Integer getCodigoToken(String token) {

        Integer codigoToken = Gramatica.TERMINAIS_E_NAO_TERMINAIS.get(token);

        if (codigoToken == null) {
            // ou é um INTEGER ou é um IDENTIFICADOR
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
}
