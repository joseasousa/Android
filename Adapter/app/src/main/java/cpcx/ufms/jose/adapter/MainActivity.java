package cpcx.ufms.jose.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cpcx.ufms.jose.adapter.adapter.CustonAdapter;
import cpcx.ufms.jose.adapter.helper.BancoHelper;
import cpcx.ufms.jose.adapter.model.Lanche;
import cpcx.ufms.jose.adapter.view.Update;

public class MainActivity extends AppCompatActivity {

    private BancoHelper bh;

    @Bind(R.id.lista)
    ListView listView;

    private List<Lanche> lanches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        bh = new BancoHelper(getBaseContext());


        lanches = bh.getAllLanches();


        CustonAdapter custonAdapter = new CustonAdapter(lanches, getApplicationContext());
        listView.setAdapter(custonAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),Update.class);
                startActivity(i);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getBaseContext(),Update.class);
                i.putExtra("lanche",lanches.get(position));
                startActivity(i);
            }
        });


    }

    private void atualizaLista() {
        lanches = bh.getAllLanches();

        CustonAdapter custonAdapter = new CustonAdapter(lanches, getApplicationContext());
        listView.setAdapter(custonAdapter);
    }
}