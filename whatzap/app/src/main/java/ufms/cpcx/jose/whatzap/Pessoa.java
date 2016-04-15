package ufms.cpcx.jose.whatzap;

import java.io.Serializable;

/**
 * Created by jose on 15/04/16.
 */
public class Pessoa implements Serializable {
    private long id;
    private String nome;

    public Pessoa(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Pessoa(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
