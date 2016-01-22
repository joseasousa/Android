package cpcx.ufms.jose.adapter;

import java.io.Serializable;

/**
 * Created by jose on 21/01/2016.
 */
public class Item implements Serializable {
    private String nome;
    private String valor;
    private int imagem;

    public Item(String nome, String valor,int imagem) {
        this.nome = nome;
        this.valor = valor;
        this.imagem = imagem;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
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
