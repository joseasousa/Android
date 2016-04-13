package cpcx.ufms.jose.restapi.server;


import java.util.List;

import cpcx.ufms.jose.restapi.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by asous on 17/03/2016.
 */
public interface UserServer {
    @POST("usuario")
    Call<User> addUser(@Body() User u);

    @GET("usuario")
    Call<List<User>> listUsers();

    @GET("usuario/{id}")
    Call<User> listUser(@Path("id") long id);

    @PUT("usuario/{id}")
    Call<User> updateUser(@Path("id") long id,@Body() User u);

    @DELETE("usuario/{id}")
    Call<User> delUser(@Path("id") long id);
}
