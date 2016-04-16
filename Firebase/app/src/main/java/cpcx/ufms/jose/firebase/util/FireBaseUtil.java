package cpcx.ufms.jose.firebase.util;

import com.firebase.client.Firebase;

/**
 * Created by asous on 16/04/2016.
 */
public class FireBaseUtil {
    //Classe para prover conexao com firebase
    private static Firebase firebase;
    private static final String URL ="https://aulajose.firebaseio.com/";


    /**
     *
     * @return  retorna conexao com firebase se a conexao ja exirtir mantem a mesma
     */
    public static Firebase getFirebase(){
        if( firebase == null ){
            firebase = new Firebase(URL);
        }
        return( firebase );
    }
}
