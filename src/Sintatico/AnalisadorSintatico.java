/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sintatico;

import Models.Token;
import java.util.Stack;
import Gramatica.Gramatica;

/**
 *
 * @author ventura
 */
public class AnalisadorSintatico {
    /**
     * Este método será implementado pelos alunos
     **/
    public void analisar(Stack<Token> tokens) {

        Stack<Token> pilhaA = tokens;
        Stack<Integer> pilhaX = new Stack<Integer>();

        // TODO: <Implemente aqui o pseudocódigo>
        pilhaX.push(52); // iniciando a pilhaX com um codigo

        while (!pilhaA.empty()) { // enquanto a pilha A for diferente de vazio faça

            Integer valorA = pilhaA.peek().getCod(); // pegando o codígo do topo da pilha A
            Integer valorX = pilhaX.peek(); // pegando o topo da pilha X

            /*
             * < 52 representa o codigo do conjunto terminal
             * >= 52 representa o codigo do conjunto não terminal
             */
            if (valorX < 52) {

                if (valorX == valorA) {
                    pilhaX.pop(); // retirando X do topo da pilha
                    pilhaA.pop(); // retirando A da entrada
                } else {
                    System.out.println("O algoritmo é invalido! ");
                    break;
                }
            } else {// se X não e terminal

                Integer[] Y1y2 = buscarValoresM_Xa(valorX, valorA);
                pilhaX.pop(); // Retire X da pilha

                if (!(Y1y2 == null)) {
                    empilharYkYk(Y1y2, pilhaX);
                }

            }
            if ((pilhaX.empty() && !pilhaA.empty()) || (!pilhaX.empty() && pilhaA.empty())) {
                System.out.println("O algoritmo é invalido");
                break;
            }

        }

        finalizaAvisandoErroOuSucesso(pilhaA, pilhaX);
    }

    private void finalizaAvisandoErroOuSucesso(Stack<Token> pilhaA, Stack<Integer> pilhaX) {
        if (pilhaX.isEmpty() && pilhaA.isEmpty()) {
            System.out.println("Programa compilado com sucesso!");
        } else {
            System.out.println("Erro");
        }
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
}
