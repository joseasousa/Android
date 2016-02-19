package cpcx.ufms.jose.adapter.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cpcx.ufms.jose.adapter.R;
import cpcx.ufms.jose.adapter.model.Lanche;

public class Update extends AppCompatActivity {

     @Bind(R.id.imagem)ImageView imagem;
     @Bind(R.id.edtValor)EditText valor;
     @Bind(R.id.edtNome)EditText nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle b = getIntent().getExtras();
        if(b!=null){
            Lanche l = (Lanche) b.get("lanche");
            updateUI(l);
        }

    }

    public void updateUI(Lanche lanche){
        if(lanche==null){
            nome.setText(null);
            imagem.setImageResource(R.drawable.cachorro_q);
            valor.setText(null);
        }else{
            nome.setText(lanche.getNome());
            imagem.setImageResource(lanche.getImagem());
            valor.setText(lanche.getValor());
        }
    }
}
