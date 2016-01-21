package cpcx.ufms.jose.adapter;

import java.io.Serializable;

/**
 * Created by jose on 21/01/2016.
 */
public class Item implements Serializable {
    private String nome;
    private String valor;

    public Item(String nome, String valor) {
        this.nome = nome;
        this.valor = valor;
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
