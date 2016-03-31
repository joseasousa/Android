package cpcx.ufms.jose.adapter.helper;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by jose on 04/02/16.
 */
@Database(name = BancoHelper.NAME, version = BancoHelper.VERSION)
public class BancoHelper {
    // Nome da Base de dados
    public static final String NAME = "lanchonete";
    // Vercao do Banco
    public static final int VERSION = 1;
}