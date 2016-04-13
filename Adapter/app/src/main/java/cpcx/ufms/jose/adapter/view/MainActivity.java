package cpcx.ufms.jose.adapter.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cpcx.ufms.jose.adapter.R;
import cpcx.ufms.jose.adapter.adapter.CustonAdapter;
import cpcx.ufms.jose.adapter.model.Lanche;

public class MainActivity extends AppCompatActivity {
    Firebase firebase;

    @Bind(R.id.lista)
    ListView listView;

    //Firebase myFirebaseRef = new Firebase("https://baseaula.firebaseio.com/");

    private List<Lanche> lanches = new ArrayList<Lanche>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        firebase = new Firebase("https://baseaula.firebaseio.com/");

        updateUi();
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("men", "update" + lanches.get(position).getNome());
                Intent i = new Intent(getBaseContext(), Update.class);
                i.putExtra("lanche", lanches.get(position));

                startActivity(i);
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }



    @OnClick(R.id.fab)
    public void onFabCad(View view) {
        Intent i = new Intent(getBaseContext(), Update.class);
        startActivity(i);
    }


    @Override
    protected void onResume() {
        super.onResume();
        updateUi();
    }

    private void updateUi() {
        lanches = SQLite.select().from(Lanche.class).queryList();

        CustonAdapter custonAdapter = new CustonAdapter(lanches, getApplicationContext());
        listView.setAdapter(custonAdapter);



    }


}