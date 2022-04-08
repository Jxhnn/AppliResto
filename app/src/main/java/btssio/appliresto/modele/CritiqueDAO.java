package btssio.appliresto.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

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

    public ArrayList<Critique> getCritiquesFromRestaurant(int idR) {
        ArrayList<Critique> lesCritiques = new ArrayList<Critique>();
        Cursor curseur;
        String req="SELECT * FROM Critique WHERE idR = " + idR + ";";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            Critique uneCritique = new Critique(idR,curseur.getString(1),curseur.getInt(2),curseur.getString(3));
            lesCritiques.add(uneCritique);
        }
        return lesCritiques;
    }

    public long modifierCrit(Critique crit){
        long ret;

        ContentValues critique = new ContentValues();
        critique.put("note",crit.getNote());
        critique.put("commentaire",crit.getCommentaire());

        SQLiteDatabase bd = accesBD.getWritableDatabase();
        ret= bd.update("critique",critique,"idR="+crit.getIdR()+" and mailU="+crit.getMailU(),null);
        Log.d("1","2");
        return ret;
    }

    public long supprimerCrit(int idR,String mail){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        ret= bd.delete("critique", "idR="+idR+" and mailU="+mail,null);
        Log.d("1","2");
        return ret;
    }


    public ArrayList<Critique> getCritiques(String mail){
        Cursor curseur;
        String req = "select * from critique WHERE mailU = '" + mail + "';";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToCritiquesArrayList(curseur);
    }

    public ArrayList<Critique> getCommentsOfRestaurant(int idR){
        Cursor curseur;
        String req = "select * from critique WHERE idR = " + idR + ";";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToCritiquesArrayList(curseur);
    }

    private ArrayList<Critique> cursorToCritiquesArrayList(Cursor curseur) {
        ArrayList<Critique> listeRelever = new ArrayList<Critique>();
        int idR;
        String mailU;
        int note;
        String commentaire;


        curseur.moveToFirst();
        while (!curseur.isAfterLast()) {
            idR = curseur.getInt(0);
            mailU = curseur.getString(1);
            note = curseur.getInt(2);
            commentaire = curseur.getString(3);
            listeRelever.add(new Critique(idR,mailU, note, commentaire));
            curseur.moveToNext();
        }
        return listeRelever;
    }
}
