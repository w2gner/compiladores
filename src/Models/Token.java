/**
 *
 * @author Felipe Costa de jesus
 * @author Pedro Ventura
 * @author 
 *
 */
package Models;

public class Token {

    private String codigo;
    private String nome;
    private String linha;
    Lista a = new Lista();

    public Token() {
    }

    public Token(String codigo, String nome, String linha) {
        this.codigo = codigo;
        this.linha = linha;
        this.nome = nome;
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
}
