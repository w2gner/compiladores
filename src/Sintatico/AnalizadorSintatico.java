package Sintatico;

import Models.Token;
import java.util.Stack;
import Gramatica.Gramatica;

/**
 *
 * @author Felipe Costa de jesus
 * @author Pedro Ventura
 * @author Wagner
 */
public class AnalizadorSintatico {

    public void analisar(Stack<Token> tokens) {
        Stack<Token> pilhaA = reverseStack((Stack<Token>) tokens);
        Stack<Integer> pilhaX = new Stack<Integer>();

        pilhaX.push(52);
        while (!pilhaA.empty()) {
            Integer valorA = pilhaA.peek().getCodigo();
            Integer valorX = pilhaX.peek();

            if (valorX < 52) {
                if (valorX == valorA) {
                    System.out.println("Tamanho PilhaA= " + pilhaA.size());
                    System.out.println("PilhaA " + pilhaA.peek().getCodigo());
                    System.out.println("PilhaX " + pilhaX.peek());
                    pilhaA.pop();
                    pilhaX.pop();
                    if (!pilhaA.empty() && !pilhaX.empty()) {
                        System.out.println("PilhaA " + pilhaA.peek().getCodigo());
                        System.out.println("PilhaX " + pilhaX.peek());
                    }
                } else {
                    System.out.println("Valor encontrado= " + getPalavra(valorA));
                    System.out.println("Valor esperado= " + getPalavra(valorX));
                    return;
                }
            } else {
                Integer[] valorAX = buscar(valorX, valorA);
                pilhaX.pop();
                if (!(valorAX == null)) {
                    empilhar(valorAX, pilhaX);
                }
            }
            if ((pilhaX.empty() && !pilhaA.empty()) || (!pilhaX.empty() && pilhaA.empty())) {
                System.out.println("pilhaX vazia= " + pilhaX.empty());
                System.out.println("pilhaA vazia= " + pilhaA.empty());
                break;
            }
        }
        validarPilhas(pilhaA, pilhaX);
    }

    private void validarPilhas(Stack<Token> pilhaA, Stack<Integer> pilhaX) {
        if (pilhaX.isEmpty() && pilhaA.isEmpty()) {
            System.out.println("O algoritmo é válido");
        } else if (pilhaA.isEmpty()) {
            System.out.println("Valor esperado= " + getPalavra(pilhaX.firstElement()));
            System.out.println("O algoritmo é invalido");
        } else {
            System.out.println("O algoritmo é invalido");
        }
        System.out.println("===Análise Sintática===");
    }

    private Stack<Token> reverseStack(Stack<Token> pilha) {
        Stack<Token> pilhaAux = new Stack<Token>();
        while (!pilha.empty()) {
            pilhaAux.push(pilha.pop());
        }
        return pilhaAux;
    }

    private Integer[] buscar(Integer x, Integer a) {
        String valor = Gramatica.GRAMATICA.get(x + "," + a);
        return Gramatica.getParsing(valor);
    }

    private void empilhar(Integer[] empilhar, Stack<Integer> pilha) {
        if (empilhar != null) {
            for (int i = empilhar.length - 1, j = 0; i >= j; i--) {
                Integer t = empilhar[i];
                pilha.push(t);
            }
        }
    }

    private String getPalavra(Integer codigo) {
        return Gramatica.getPalavra(codigo);
    }
}
