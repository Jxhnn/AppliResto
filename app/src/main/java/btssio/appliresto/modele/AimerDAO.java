package btssio.appliresto.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
        value.put("idR", unLike.getIdR());
        value.put("mailU",unLike.getMailU());
        value.put("aime",unLike.getAime());

        ret = bd.insert("aimer", null, value);
        return ret;
    }

    public Aimer getAimer(String email, int idRestaurant) {
        SQLiteDatabase bd = accesBD.getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM aimer WHERE mailU = '" + email
                + "' AND idR = " + idRestaurant, null);

        Aimer liked = null;
        if (cursor.getCount() == 0) {
            Aimer addLike = new Aimer(idRestaurant, email, false);
            addAime(addLike);
            Log.d("LIKED: ", "ADDED THE LIKE");
        }

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            boolean isLiked = false;
            if (cursor.getInt(2) > 0) isLiked = true;
            liked = new Aimer(cursor.getInt(0), cursor.getString(1), isLiked);

        }
        return liked;
    }

    public long updateLike(Aimer currentLike) {
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues aimer = new ContentValues();
        aimer.put("aime",currentLike.getAime());
        int aime = 0;
        if (currentLike.getAime()) aime = 1;
        else aime = 0;


        long ret = bd.update("aimer",aimer,"idR = " + currentLike.getIdR() + " AND mailU = '"
                + currentLike.getMailU() + "'", null);

        return ret;
    }


}
