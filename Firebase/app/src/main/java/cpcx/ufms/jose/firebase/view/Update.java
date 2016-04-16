package cpcx.ufms.jose.firebase.view;


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
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.firebase.client.Firebase;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cpcx.ufms.jose.firebase.R;
import cpcx.ufms.jose.firebase.model.Lanche;
import cpcx.ufms.jose.firebase.util.FireBaseUtil;

public class Update extends AppCompatActivity {
    private Firebase firebase;

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

    private String localFoto;

    private static final int FOTO = 1;

    private boolean fotoResource = false;

    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebase = FireBaseUtil.getFirebase();
        updateUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
        lanche = new Lanche();
        Bundle b = getIntent().getExtras();

        /**
         * verefica se foi enviado
         */
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
        /**
         * pego o link do objeto e o id de onde ele esta e removo o no
         */
        firebase.child("lanche").child(lanche.getId()).removeValue();
        finish();
    }


    @OnClick(R.id.fabCad)
    public void fabcad(View v) {
        Firebase posRef = firebase.child("lanche");

        Firebase newPost ;
        lanche.setNome(edtNome.getText().toString());
        lanche.setValor(edtValor.getText().toString());

        lanche.setImagem((String) imvImagem.getTag());

        if (update) {
            /**
             * pego o link do objeto e o id de onde ele esta e atualizo o no
             */
            newPost = posRef.child(lanche.getId());
        } else {
            newPost = posRef.push();
            lanche.setId(newPost.getKey());
        }
        newPost.setValue(lanche);

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

    /**
     * @see metodo que chama a camera  e espera o retorno salva a imagem resultante
     */
    public void carregaFoto() {
        fotoResource = true;
        localFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";

        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(localFoto)));

        startActivityForResult(intentCamera, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == FOTO) {
                if (resultCode == Activity.RESULT_OK) {
                    setFoto(localFoto);
                } else {
                    this.localFoto = null;
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
    public void foto(View v) {
        carregaFoto();
    }
}
