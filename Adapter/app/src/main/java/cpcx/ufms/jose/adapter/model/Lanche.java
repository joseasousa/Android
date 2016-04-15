package cpcx.ufms.jose.adapter.model;

import java.io.Serializable;

/**
 * Created by jose on 21/01/2016.
 */
//@Table(database = BancoHelper.class)
public class Lanche  implements Serializable {

  //  @PrimaryKey(autoincrement = true)
    private long id;
    //@Column
    private String nome;
    //@Column
    private String valor;
    //@Column
    private String imagem;



    public Lanche() {
    }

    public Lanche(String nome, String valor, String imagem) {
        this.nome = nome;
        this.valor = valor;
        this.imagem = imagem;
    }

    public Lanche(long id, String nome, String valor, String imagem) {
        this.nome = nome;
        this.valor = valor;
        this.imagem = imagem;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
