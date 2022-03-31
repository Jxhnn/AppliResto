package btssio.appliresto.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mick.souloumiac1 on 17/03/2022.
 */

public class UserDAO {

    private static String base = "BDresto";
    private static int version = 1;
    BD_SQLiteOpenHelper accesBD;

    public UserDAO(Context ct){
        accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);
    }


    public long updateUser(User newUser, String mailU){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("pseudoU", newUser.getPseudoU());
        value.put("mdpU", newUser.getMdpU());
        ret = bd.update("User", null, "mailU" + " = " + mailU, null);

        return ret;
    }
}
