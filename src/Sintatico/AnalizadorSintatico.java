package Sintatico;

import Models.Token;
import java.util.Stack;

import javax.swing.JOptionPane;

import Gramatica.Gramatica;

/**
 *
 * @author Felipe Costa de jesus
 * @author Pedro Ventura
 * @author Wagner
 */
public class AnalizadorSintatico {
    /*
     * Função para mostrar tela de erro com a linha e palavra
     */

    String msg;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void erro(String msg) {
        JOptionPane.showMessageDialog(null,
                "Erro Sintática houve uma interrupção\n" + msg, "",
                JOptionPane.ERROR_MESSAGE);
        // erroLexico = true;
    }

    public void analisar(Stack<Token> tokens) {
        Stack<Token> pilhaA = reverseStack((Stack<Token>) tokens);
        Stack<Integer> pilhaX = new Stack<Integer>();

        pilhaX.push(52);
        while (!pilhaA.empty()) {
            Integer valorA = pilhaA.peek().getCodigo();
            Integer valorX = pilhaX.peek();

            if (valorX < 52) {
                if (valorX == valorA) {
                    pilhaA.pop();
                    pilhaX.pop();
                } else {
                    setMsg("O algoritmo é invalido valores diferentes! ");
                    // System.out.println("Valor encontrado= " + getPalavra(valorA));
                    // System.out.println("Valor esperado= " + getPalavra(valorX));
                    // System.out.println("===Análise Sintática===");
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
            JOptionPane.showMessageDialog(null, "O algoritmo é válido");
            System.out.println("O algoritmo é válido");
        } else if (pilhaA.isEmpty()) {

            System.out.println("Valor esperado: " + getPalavra(pilhaX.firstElement()));
            erro("Valor esperado: " + getPalavra(pilhaX.firstElement()) + "\n" + getMsg());
            System.out.println("O algoritmo é invalido");
        } else {
            erro("O algoritmo é invalido");
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
