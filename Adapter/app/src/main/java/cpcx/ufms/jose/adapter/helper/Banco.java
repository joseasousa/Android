package cpcx.ufms.jose.adapter.helper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cpcx.ufms.jose.adapter.model.Lanche;

/**
 * Created by jose .
 */
public class Banco extends SQLiteOpenHelper {

    private SQLiteDatabase db;


    public Banco(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE lanche(_id INTEGER PRIMARY KEY  AUTOINCREMENT,nome TEXT, valor TEXT, imagem integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS lanche";
        db.execSQL(sql);

    }

    public Cursor buscar(String sql) {
        return getWritableDatabase().rawQuery(sql, null);
    }

    public int updateLanche(Lanche lanche) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", lanche.getNome());
        values.put("valor", lanche.getValor());
        // updating row
        return db.update("lanches", values, "_id = ?", new String[]{String.valueOf(lanche.getId())});
    }

    public void deleteLanche(Lanche lanche) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("lanche", "_id = ?", new String[]{String.valueOf(lanche.getId())});
        db.close();
    }
}
