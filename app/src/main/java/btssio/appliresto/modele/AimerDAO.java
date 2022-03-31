package btssio.appliresto.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mick.souloumiac1 on 17/03/2022.
 */

public class AimerDAO {

    private static String base = "BDresto";
    private static int version = 1;
    BD_SQLiteOpenHelper accesBD;

    public AimerDAO(Context ct){
        accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);
    }

    public long addAime(Aimer unLike){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("", unLike.getIdR());
        value.put("",unLike.getMailU());
        value.put("",unLike.getAime());

        ret = bd.insert("aimer", null, value);
        return ret;
    }


}
