/**
 *
 * @author Felipe Costa de jesus
 * @author Pedro Ventura
 * @author Wagner
 *
 */
package Models;

import java.util.ArrayList;
import java.util.Stack;

public class Lista {

    private final Stack<Token> lista;

    public Lista() {
        lista = new Stack<Token>();
    }

    public Stack<Token> getLista() {
        return lista;
    }

    public void adicionar(Token clas) {
        lista.add(clas);
    }

    public void limpar() {
        lista.clear();
    }
    
     public int tamanho() {
        return lista.size();
    }
    
    public Token retorna(int posicao){
        return lista.get(posicao);
    }
}