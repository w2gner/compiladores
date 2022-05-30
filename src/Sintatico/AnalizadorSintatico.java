package Sintatico;

import Models.Token;
import java.util.Stack;
import Gramatica.Gramatica;

/**
 *
 * @author Pedro Ventura
 * @author Wagner
 */
public class AnalizadorSintatico {

    /**
     * Este método será implementado pelos alunos
     *
     */
    public void analisar(Stack<Token> tokens) {
        Stack<Token> pilhaA = reverseStack((Stack<Token>)tokens);
        Stack<Integer> pilhaX = new Stack<Integer>();

        pilhaX.push(52); // iniciando a pilhaX com um codigo
        while (!pilhaA.empty()) { // enquanto a pilha A for diferente de vazio faça     
            Integer valorA = pilhaA.peek().getCodigo(); // pegando o codígo do topo da pilha A
            //Integer valorA = pilhaA.get(index).getCodigo(); // pegando o codígo do topo da pilha A
            Integer valorX = pilhaX.peek(); // pegando o topo da pilha
           
            /*
             * < 52 representa o codigo do conjunto terminal
             * >= 52 representa o codigo do conjunto não terminal
             */

            if (valorX < 52) {
                if (valorX == valorA) {
                    System.out.println("Tamanho PilhaA= " + pilhaA.size());
                    System.out.println("PilhaA " + pilhaA.peek().getCodigo());
                    System.out.println("PilhaX " + pilhaX.peek());
                    pilhaA.pop(); // retirando A da entrada
                    pilhaX.pop(); // retirando X do topo da pilha
                    if(!pilhaA.empty() && !pilhaX.empty()){
                        System.out.println("PilhaA " + pilhaA.peek().getCodigo());
                        System.out.println("PilhaX " + pilhaX.peek());
                    }
                } else {
                    System.out.println("Valor encontrado= " + getBuscarPalavraPeloCodigo(valorA));
                    System.out.println("Valor esperado= " + getBuscarPalavraPeloCodigo(valorX));
                    // System.out.println("O algoritmo é invalido valores diferentes! ");
                    return;
                }
            } else {// se X não e terminal
                Integer[] Y1y2 = buscarValoresM_Xa(valorX, valorA);
                pilhaX.pop(); // Retire X da pilha
                if (!(Y1y2 == null)) {
                    empilharYkYk(Y1y2, pilhaX);
                }
            }
            if ((pilhaX.empty() && !pilhaA.empty()) || (!pilhaX.empty() && pilhaA.empty())) {
                System.out.println("pilhaX vazia= " + pilhaX.empty());
                System.out.println("pilhaA vazia= " + pilhaA.empty());
                break;
            }
        }
        finalizaAvisandoErroOuSucesso(pilhaA, pilhaX);
    }

    private void finalizaAvisandoErroOuSucesso(Stack<Token> pilhaA, Stack<Integer> pilhaX) {
        if (pilhaX.isEmpty() && pilhaA.isEmpty()) {
            System.out.println("O algoritmo é válido");
        } else {
            System.out.println("O algoritmo é invalido");
        }
        System.out.println("===Análise Sintática===");
    }

    private Integer[] buscarValoresM_Xa(Integer x, Integer a) {
        String producoes = Gramatica.GRAMATICA.get(x + "," + a);
        return Gramatica.geraDadosCruzamentoTabParsingToken(producoes);
    }

    /**
     * Adiciona os elementos na pilha na order contrária ao array
     */
    private void empilharYkYk(Integer[] empilhar, Stack<Integer> pilha) {
        if (empilhar != null) {
            for (int i = empilhar.length - 1, j = 0; i >= j; i--) {
                Integer t = empilhar[i];
                pilha.push(t);
            }
        }

    }

    private String getBuscarPalavraPeloCodigo(Integer codigo) {

        return Gramatica.getBuscarPalavraPeloCodigo(codigo);
    }

    private Stack<Token> reverseStack(Stack<Token> pilha) {
        Stack<Token> pilhaAux = new Stack<Token>();
        while (!pilha.empty()) {
            pilhaAux.push(pilha.pop());
        }
        return pilhaAux;
    }
}
