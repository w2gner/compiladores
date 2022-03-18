package src;

public class analisadorLexico {
    int CODIGO; // armazenará um código numérico relativo ao token encontrado (conforme tabela
                // fornecida acima).
    char CAR; // armazenará ao próximo caráter a ser analisado.
    int VALOR; // armazenará o valor na base 10 de constantes inteiras encontradas (token =
               // inteiro).
    String[] BUFFER_IDENT; // armazenará os identificadores encontrados (token = identificador).
    char[] BUFFER_LITERAL; // armazenará a cadeia relativa a literais (token= literal)

    // pega o próximo carácter no programa fonte e o coloca na variável CAR
    private char PEGACAR() {
        return 1;
    }

    // verifica se token = identificador está na tabela de palavras reservadas. Caso
    // não esteja trata-se realmente do token = identificador. Se estiver trata-se
    // de palavra reservada cujo código está na própria tabela
    public int BUSCA_PALAVRA_RESERVADA(String token) {
        gramatica x = new gramatica();

        if (token != "" && token != null || token != " ") {
            if (x.gramatica.containsKey(token)) {
                BUFFER_IDENT[BUFFER_IDENT.length] = token;
                return x.gramatica.get(token);
            } else {
                BUFFER_IDENT[BUFFER_IDENT.length] = token;
                return 25; // Caso não encontrado é um identificador
            }
        } else {
            System.out.println("0 token recebido é inválido");
            return (0);
        }
    }
}
