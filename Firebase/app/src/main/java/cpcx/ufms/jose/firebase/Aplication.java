package cpcx.ufms.jose.firebase;

import android.app.Application;

/**
 * Created by jose on 31/03/16.
 */
public class Aplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        com.firebase.client.Firebase.setAndroidContext(this);

    }
}
