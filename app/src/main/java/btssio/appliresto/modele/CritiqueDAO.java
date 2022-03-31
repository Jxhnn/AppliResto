package btssio.appliresto.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mick.souloumiac1 on 17/03/2022.
 */

public class CritiqueDAO {

    private static String base = "BDresto";
    private static int version = 1;
    BD_SQLiteOpenHelper accesBD;


    public CritiqueDAO(Context ct){
        accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);
    }

    public long addCritique(Critique uneCritique){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("idR", uneCritique.getIdR());
        value.put("mailU", uneCritique.getMailU());
        value.put("note",uneCritique.getNote());
        value.put("commentaire",uneCritique.getCommentaire());
        ret = bd.insert("Critique", null, value);
        return ret;
    }
}
