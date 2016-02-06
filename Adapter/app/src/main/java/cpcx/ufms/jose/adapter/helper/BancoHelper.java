package cpcx.ufms.jose.adapter.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import cpcx.ufms.jose.adapter.model.Lanche;

/**
 * Created by jose on 04/02/16.
 */
public class BancoHelper extends SQLiteOpenHelper {


    // Vercao do Banco
    private static final int DATABASE_VERSION = 1;

    // Nome da Base de dados
    private static final String DATABASE_NAME = "lanchonete";

    // Nome da tabela
    private static final String TABLE_NAME = "lanche";



    public BancoHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Criando tabela
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE lanche(_id INTEGER PRIMARY KEY  AUTOINCREMENT,nome TEXT, valor TEXT, imagem integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Deletando tabela existente
        db.execSQL("DROP TABLE IF EXISTS lanche" );
        // Criando nova tabela
        onCreate(db);
    }

    /**
     *  CRUD(Create, Read, Update, Delete)
     */

    // Adicionando novo Lanche
    public void addLanche(Lanche lanche) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", lanche.getNome());
        values.put("valor", lanche.getValor());
        values.put("imagem", lanche.getImagem());

        // Inserindo valor
        db.insert(TABLE_NAME, null, values);
        //Fechando conexao
        db.close();
    }

    // Retorna um unico lanche
    public  Lanche getLanche(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT _id,  nome,valor,imagem FROM lanche where _id = ?";

        Cursor cursor = db.rawQuery(sql,new String[]{ String.valueOf(id)});

        if (cursor != null){
            cursor.moveToFirst();

        Lanche lanche = new Lanche();
        lanche.setId(cursor.getInt(0));
        lanche.setNome(cursor.getString(1));
        lanche.setValor(cursor.getString(2));
        lanche.setImagem(cursor.getInt(3));

        return lanche;

        }
        return  null;

    }

    // Obtendo todos os lanches
    public List<Lanche> getAllLanches() {
        List<Lanche> lanches = new ArrayList<Lanche>();
        // Sql da consulta da base de dados
        String sql = "SELECT _id,  nome,valor,imagem FROM lanche";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        // looping Povoando a lista de Lanches
        if (cursor.moveToFirst()) {
            do {
                Lanche lanche = new Lanche();
                lanche.setId(cursor.getInt(0));
                lanche.setNome(cursor.getString(1));
                lanche.setValor(cursor.getString(2));
                lanche.setImagem(cursor.getInt(3));
                // Adicionando elemento a lista
                lanches.add(lanche);
            } while (cursor.moveToNext());
        }
        return lanches;
    }

    // Updating um unico lanche
    public int updateLanche(Lanche lanche) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", lanche.getNome());
        values.put("valor", lanche.getValor());
        values.put("imagem",lanche.getImagem());

        // updating row
        return db.update(TABLE_NAME, values,  "_id = ?",
                new String[] { String.valueOf(lanche.getId()) });
    }

    // Delettando um lanche
    public void deleteLanche(Lanche lanche) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, " _id= ?",
                new String[] { String.valueOf(lanche.getId()) });
        db.close();
    }

    // Obter quantidade de Lanches
    public int getLanchesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }
}