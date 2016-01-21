package cpcx.ufms.jose.adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Item> itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView =(ListView)findViewById(R.id.listItens);
        itens=new ArrayList<Item>();
        Popula();
        CustonAdapter custonAdapter = new CustonAdapter(itens,getApplicationContext());
        listView.setAdapter(custonAdapter);
}

    private void Popula(){
        itens.add(new Item("x-bagunsa","13,00"));
        itens.add(new Item("x-bagunsa","13,00"));
        itens.add(new Item("x-bagunsa","13,00"));
        itens.add(new Item("x-bagunsa","13,00"));

    }
}
