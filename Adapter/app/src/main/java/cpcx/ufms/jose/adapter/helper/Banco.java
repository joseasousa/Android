package cpcx.ufms.jose.adapter.helper;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jose .
 */
public class Banco extends SQLiteOpenHelper {

    public Banco(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE lanche(_id integer primarry key,nome TEXT, valor TEXT)";
        db.execSQL(sql);
        sql = "INSERT INTO lanche VALUES(1,'X-Salada','6,5')";
        db.execSQL(sql);
        sql = "INSERT INTO lanche VALUES(1,'X-File','9,2')";
        db.execSQL(sql);
        sql = "INSERT INTO lanche VALUES(1,'X-Tudo','10,2')";
        db.execSQL(sql);
        sql = "INSERT INTO lanche VALUES(1,'X-Teiga','2,2')";
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



}
