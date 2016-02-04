package cpcx.ufms.jose.adapter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import cpcx.ufms.jose.adapter.adapter.CustonAdapter;
import cpcx.ufms.jose.adapter.helper.BancoHelper;
import cpcx.ufms.jose.adapter.model.Lanche;

public class MainActivity extends AppCompatActivity {


    private BancoHelper bh;

    private ListView listView;
    private List<Lanche> lanches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lista);


         bh = new BancoHelper(getBaseContext());


        lanches = bh.getAllLanches();


        CustonAdapter custonAdapter = new CustonAdapter(lanches, getApplicationContext());
        listView.setAdapter(custonAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lanche lanche = new Lanche();
                lanche.setNome("lanche" + lanches.size());
                lanche.setValor("300,00");
                lanche.setImagem(R.drawable.x_tudo);
                bh.addLanche(lanche);

                lanches.add(lanche);


                CustonAdapter custonAdapter = new CustonAdapter(lanches, getApplicationContext());
                listView.setAdapter(custonAdapter);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Funcao de delete
                //bh.deleteLanche(lanches.get(position));
                lanches.get(position).setImagem(R.drawable.cachorro_quente);
                //usando funcao de update
                bh.updateLanche(lanches.get(position));

                atualizaLista();
            }
        });
    }

    private void atualizaLista(){
        lanches= bh.getAllLanches();

        CustonAdapter custonAdapter = new CustonAdapter(lanches, getApplicationContext());
        listView.setAdapter(custonAdapter);
    }







}