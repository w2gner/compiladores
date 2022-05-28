/**
 *
 * @author Felipe Costa de jesus
 * @author Pedro Ventura
 * @author Wagner
 *
 */
package Models;

public class Token {

    private String codigo;
    private Integer cod;
    private String nome;
    private String linha;
    private String palavra;

    Lista a = new Lista();

    public Token() {
    }

    public Token(String codigo, String nome, String linha) {
        this.codigo = codigo;
        this.linha = linha;
        this.nome = nome;
    }

    public Token(String palavra, Integer cod) {
        this.palavra = palavra;
        this.cod = cod;

    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getLinha() {
        return linha;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNome(String token) {
        this.nome = token;
    }

    public void setlinha(String linha) {
        this.linha = linha;
    }

    public Integer getCod() {
        return cod;
    }

    @Override
    public String toString() {
        return "Token{" + "palavra=" + palavra + ", codigo=" + codigo + '}';
    }
}
