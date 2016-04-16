package cpcx.ufms.jose.firebase.model;

import java.io.Serializable;

/**
 * Created by jose on 21/01/2016.
 */

public class Lanche  implements Serializable {


    private String id;

    private String nome;

    private String valor;

    private String imagem;

    public Lanche() {
    }

    public Lanche(String nome, String valor, String imagem) {
        this.nome = nome;
        this.valor = valor;
        this.imagem = imagem;
    }

    public Lanche(String id, String nome, String valor, String imagem) {
        this.nome = nome;
        this.valor = valor;
        this.imagem = imagem;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
