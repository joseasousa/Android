package cpcx.ufms.jose.restapi.model;

import java.io.Serializable;

/**
 * Created by asous on 17/03/2016.
 */
public class User implements Serializable {
    private  long id;

    private String nome;

    private String email;

    private int idade;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
