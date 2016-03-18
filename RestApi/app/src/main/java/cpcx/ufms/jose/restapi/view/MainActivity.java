package cpcx.ufms.jose.restapi.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cpcx.ufms.jose.restapi.R;
import cpcx.ufms.jose.restapi.model.User;
import cpcx.ufms.jose.restapi.server.UserServer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.edtNome)
    EditText edtNome;

    @Bind(R.id.edtEmail)
    EditText edtEmail;

    @Bind(R.id.edtId)
    EditText edtId;

    @Bind(R.id.edtIdade)
    EditText edtIdade;

    @Bind(R.id.tvResul)
    TextView tvResul;

    private List<User> users;

    private final String url = "http://192.168.1.5:1337/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        updateUI();
    }

    //Botao de inserção
    @OnClick(R.id.fab)
    public void addUser(View v) {
        UserServer userServer = retrofit.create(UserServer.class);

        User u = getUser();

        userServer.addUser(u).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(getBaseContext(), "Usuario adicionado", Toast.LENGTH_LONG).show();
                updateUI();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }


    //metodo que deleta um usuario
    @OnClick(R.id.btDelete)
    public void delUser(View v) {
        long id = Long.parseLong(edtId.getText().toString());

        UserServer userServer = retrofit.create(UserServer.class);
        userServer.delUser(id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(getBaseContext(), "Usuario removido", Toast.LENGTH_LONG).show();
                updateUI();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    //metodo que lista um usuario pelo id
    @OnClick(R.id.btBuscaId)
    public void listUser(View v) {
        long id = Long.parseLong(edtId.getText().toString());

        UserServer userServer = retrofit.create(UserServer.class);
        userServer.listUser(id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User u = response.body();
                String a = u.getNome() + " : " + u.getId();
                tvResul.setText(a);
                Log.d("codigo: ", String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    //metodo que atualiza o usuario
    @OnClick(R.id.btUpdate)
    public void updateUser(View v) {
        long id = Long.parseLong(edtId.getText().toString());

        UserServer userServer = retrofit.create(UserServer.class);
        User u = getUser();

        userServer.updateUser(id, u).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("codigo: ", String.valueOf(response.code()));
                updateUI();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    //metodo para atualizar os valores na aplicação
    private void updateUI() {
        UserServer userServer = retrofit.create(UserServer.class);

        userServer.listUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                users = response.body();
                String res = "";
                for (User u : users) {
                    res = res + u.getNome() + " : " + u.getId() + "\n";
                }
                tvResul.setText(res);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

    private User getUser() {
        User u = new User();
        u.setNome(edtNome.getText().toString());
        u.setEmail(edtEmail.getText().toString());
        u.setIdade(Integer.parseInt(edtIdade.getText().toString()));
        return u;
    }

}
