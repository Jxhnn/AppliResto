package btssio.appliresto.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by mick.souloumiac1 on 17/03/2022.
 */

public class RestoDAO {

    private static String base = "BDresto";
    private static int version = 1;
    BD_SQLiteOpenHelper accesBD;

    public RestoDAO(Context ct){
        accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);
    }

    public long addResto(Resto unResto){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("nomR", unResto.getNomR());
        value.put("numAdrR",unResto.getNumAdrR());
        value.put("voieAdrR",unResto.getVoieAdrR());
        value.put("cpR",unResto.getCpR());
        value.put("villeR",unResto.getVilleR());
        value.put("latitudeDegR",unResto.getLatitudeDegR());
        value.put("longitudeDegR",unResto.getLongitudeDegR());
        value.put("descR",unResto.getDescR());
        value.put("horaireR",unResto.getHorairesR());

        ret = bd.insert("Resto", null, value);
        return ret;
    }

    public Resto getUnResto(int id){
        Cursor curseur;
        Resto unResto = null;
        String req="SELECT * FROM resto WHERE idR="+id;
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            unResto = new Resto(id,curseur.getString(1),curseur.getInt(2),curseur.getString(3),curseur.getInt(4),curseur.getString(5),curseur.getFloat(6),curseur.getFloat(7),curseur.getString(8),curseur.getString(9));
        }
        return unResto;
    }

    public void supprimerResto(Resto unResto){
        Cursor curseur;
        String req="DELETE FROM resto WHERE idR="+unResto.getIdR()+";";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
    }

    public void modifierResto(Resto unResto){

        Cursor curseur;
        String req="Update resto SET nomR="+unResto.getNomR()+",numAdrR="+unResto.getNumAdrR()+",voieAdrR="+unResto.getVoieAdrR()+",cpR="+unResto.getCpR()+", villeR="+unResto.getVilleR()+",latitudeDegR="+unResto.getLatitudeDegR()+",longitudeDegR="+unResto.getLongitudeDegR()+",descR="+unResto.getDescR()+",horairesR="+unResto.getHorairesR()+" WHERE idR="+unResto.getIdR()+";";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
    }

    public ArrayList<Resto> getRestos(){
        Cursor curseur;
        String req = "select * from Resto";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToRestoArrayList(curseur);
    }

    private ArrayList<Resto> cursorToRestoArrayList(Cursor curseur) {
        ArrayList<Resto> listeRelever = new ArrayList<Resto>();
        int idR;
        String nomR;
        int numAdrR;
        String voieAdrR;
        int cpC;
        String villeR;
        float latitudeDegR;
        float longitudeDegR;
        String descR;
        String horairesR;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()) {
            idR = curseur.getInt(0);
            nomR = curseur.getString(1);
            numAdrR = curseur.getInt(2);
            voieAdrR = curseur.getString(3);
            cpC = curseur.getInt(4);
            villeR = curseur.getString(5);
            latitudeDegR = curseur.getFloat(6);
            longitudeDegR = curseur.getFloat(7);
            descR = curseur.getString(8);
            horairesR = curseur.getString(9);
            listeRelever.add(new Resto(idR,nomR, numAdrR, voieAdrR, cpC,villeR,latitudeDegR,longitudeDegR,descR,horairesR));
            curseur.moveToNext();
        }
        return listeRelever;
    }

}
