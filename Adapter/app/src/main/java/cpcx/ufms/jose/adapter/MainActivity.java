package cpcx.ufms.jose.adapter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cpcx.ufms.jose.adapter.adapter.CustonAdapter;
import cpcx.ufms.jose.adapter.helper.Banco;
import cpcx.ufms.jose.adapter.model.Lanche;

public class MainActivity extends AppCompatActivity {
    Banco b = new Banco(getBaseContext(), "lanche", null, 1);
    private ListView listView;
    private List<Lanche> lanches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lista);

        consultaBanco();
        CustonAdapter custonAdapter = new CustonAdapter(lanches, getApplicationContext());
        listView.setAdapter(custonAdapter);
    }



    private void consultaBanco() {
        String sql = "SELECT  nome,valor FROM lanche";
        lanches = new ArrayList<Lanche>();
        Cursor cursor = b.buscar(sql);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                Lanche l = new Lanche();
                l.setNome(cursor.getString(0));
                l.setValor(cursor.getString(1));
                lanches.add(l);
                cursor.moveToNext();
            }
        }
    }

    private void Insere(Lanche lanche) {

        SQLiteDatabase base = b.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", lanche.getNome());
        values.put("valor", lanche.getValor());

        long resultado = base.insert("lanche", null, values);

        if (resultado != -1) {
            Toast.makeText(this, "Deu Certo", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show();
        }
    }


}
