/**
 *
 * @author Felipe Costa de jesus
 * @author Pedro Ventura
 * @author Wagner
 *
 */
package Models;

public class Token {

    private Integer codigo;
    private String nome;
    private Integer linha;

    public Token() {
    }

    public Token(Integer codigo, String nome, Integer lineNumber) {
        super();
        this.codigo = codigo;
        this.linha = lineNumber;
        this.nome = nome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public Integer getLinha() {
        return linha;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setNome(String token) {
        this.nome = token;
    }

    public void setlinha(Integer linha) {
        this.linha = linha;
    }

    public String toString() {
        return "Token{" + "palavra=" + nome + ", codigo=" + codigo + '}';
    }
}
