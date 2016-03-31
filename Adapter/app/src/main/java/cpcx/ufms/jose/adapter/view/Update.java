package cpcx.ufms.jose.adapter.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cpcx.ufms.jose.adapter.R;
import cpcx.ufms.jose.adapter.model.Lanche;

public class Update extends AppCompatActivity {

    @Bind(R.id.imagem)
    ImageView imvImagem;
    @Bind(R.id.edtValor)
    EditText edtValor;
    @Bind(R.id.edtNome)
    EditText edtNome;

    @Bind(R.id.fabDel)
    FloatingActionButton fbDell;

    private boolean update = false;
    private Lanche lanche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lanche = new Lanche();
        Bundle b = getIntent().getExtras();
        if (b != null) {
            lanche = (Lanche) b.get("lanche");
            update = true;
            fbDell.setVisibility(View.VISIBLE);
            updateUI();
        }
    }

    @OnClick(R.id.fabDel)
    public void fabDel(View view){
        lanche.delete();
        finish();
    }


    @OnClick(R.id.fabCad)
    public void fabcad(View v) {

        lanche.setNome(edtNome.getText().toString());
        lanche.setValor(edtValor.getText().toString());
        if (update) {

            lanche.update();
        } else {
            lanche.setNome(edtNome.getText().toString());
            lanche.setValor(edtValor.getText().toString());
            lanche.save();
        }
        finish();
    }


    public void updateUI() {
        if (lanche == null) {
            edtNome.setText(null);
            imvImagem.setImageResource(R.drawable.cachorro_q);
            edtValor.setText(null);
        } else {
            edtNome.setText(lanche.getNome());
            imvImagem.setImageResource(lanche.getImagem());
            edtValor.setText(lanche.getValor());
        }
    }
}
