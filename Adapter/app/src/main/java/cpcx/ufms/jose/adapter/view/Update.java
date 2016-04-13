package cpcx.ufms.jose.adapter.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.firebase.client.Firebase;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cpcx.ufms.jose.adapter.R;
import cpcx.ufms.jose.adapter.model.Lanche;

public class Update extends AppCompatActivity {

    @Bind(R.id.imagem)
    ImageView imvImagem;
    Firebase firebase;

    @Bind(R.id.edtValor)
    EditText edtValor;
    @Bind(R.id.edtNome)
    EditText edtNome;

    @Bind(R.id.fabDel)
    FloatingActionButton fbDell;

    private boolean update = false;
    private Lanche lanche;

    private String localFoto;

    private static final int FOTO = 1;

    private boolean fotoResource = false;

    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update);

        Firebase.setAndroidContext(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        firebase = new Firebase("https://baseaula.firebaseio.com/");



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lanche = new Lanche();
        Bundle b = getIntent().getExtras();
        if (b != null) {
            lanche = (Lanche) b.get("lanche");
            update = true;
            fbDell.setVisibility(View.VISIBLE);
            updateUI();
            localFoto = lanche.getImagem();
        }
    }



    @OnClick(R.id.fabDel)
    public void fabDel(View view) {
        lanche.delete();
        finish();
    }


    @OnClick(R.id.fabCad)
    public void fabcad(View v) {

        lanche.setNome(edtNome.getText().toString());
        lanche.setValor(edtValor.getText().toString());
        if (update) {
            lanche.setImagem((String) imvImagem.getTag());

            lanche.update();
        } else {
            lanche.setNome(edtNome.getText().toString());
            lanche.setValor(edtValor.getText().toString());
            lanche.setImagem((String) imvImagem.getTag());

            Log.i("Teste",(String) imvImagem.getTag());

            lanche.save();

            List<Lanche> lanches = SQLite.select().from(Lanche.class).queryList();

        }
        firebase.child("lanche").push().setValue(lanche);
        finish();
    }


    public void updateUI() {
        if (lanche == null) {
            edtNome.setText(null);            
            edtValor.setText(null);
        } else {
            edtNome.setText(lanche.getNome());
            setFoto(lanche.getImagem());
            edtValor.setText(lanche.getValor());
        }
    }

    public void carregaFoto() {
        fotoResource = true;
        localFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";

        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(localFoto)));

        startActivityForResult(intentCamera, 1);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!fotoResource) {
            if (resultCode == -1) {
                InputStream stream = null;

                try {
                    if (bitmap != null) {
                        bitmap.recycle();
                    }

                    stream = getContentResolver().openInputStream(data.getData());
                    bitmap = BitmapFactory.decodeStream(stream);
                    imvImagem.setImageBitmap(bitmap);
                    localFoto=data.getDataString();



                } catch (Exception e){
                    e.printStackTrace();
                }
            }

        }else{
        if (requestCode == FOTO) {
            if (resultCode == Activity.RESULT_OK) {
                setFoto(localFoto);
            } else {
                this.localFoto = null;
            }
        }
        }

    }

    private void setFoto(String url) {
        if (url != null) {
            Bitmap imagemFoto = BitmapFactory.decodeFile(url);
            imvImagem.setImageBitmap(imagemFoto);
            imvImagem.setTag(url);
        }
    }

    @OnClick(R.id.fabFhoto)
    public  void foto(View v){
        carregaFoto();
    }
}
