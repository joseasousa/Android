package cpcx.ufms.jose.adapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<Item>  itens=new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView =(ListView)findViewById(R.id.lista);

        Popula();
        CustonAdapter custonAdapter = new CustonAdapter(itens,getApplicationContext());
        listView.setAdapter(custonAdapter);
}

    private void Popula(){
        itens.add(new Item("X-Salada", "9,00", R.drawable.x_salada));
        itens.add(new Item("X-Bacon", "12,50", R.drawable.x_bacon));
        itens.add(new Item("Cachorro-quente prensado", "6,00", R.drawable.cachorro_quente));
        itens.add(new Item("Misto-quente","6,00", R.drawable.misto_quente));
        itens.add(new Item("X-Egg", "13,00", R.drawable.x_egg));
        itens.add(new Item("Bauru", "4,00", R.drawable.bauru));
        itens.add(new Item("X-Calabresa", "11,60", R.drawable.x_calabresa));
        itens.add(new Item("X-Americano", "13,00", R.drawable.x_americano));
        itens.add(new Item("X-Tudo", "23,00", R.drawable.x_tudo));
        itens.add(new Item("Cachorro-quente", "5,00", R.drawable.cachorro_q));
    }
}
