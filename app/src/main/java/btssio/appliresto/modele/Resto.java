package btssio.appliresto.modele;

/**
 * Created by mick.souloumiac1 on 17/03/2022.
 */

public class Resto {

    private int idR;
    private String nomR;
    private int numAdrR;
    private String voieAdrR;
    private int cpR;
    private String villeR;
    private float latitudeDegR;
    private float longitudeDegR;
    private String descR;
    private String horairesR;
    private String photoPrincipal;

    public Resto(int idR, String nomR, int numAdrR, String voieAdrR, int cpR, String villeR, float latitudeDegR, float longitudeDegR, String descR, String horairesR, String photoPrincipal) {
        this.idR = idR;
        this.nomR = nomR;
        this.numAdrR = numAdrR;
        this.voieAdrR = voieAdrR;
        this.cpR = cpR;
        this.villeR = villeR;
        this.latitudeDegR = latitudeDegR;
        this.longitudeDegR = longitudeDegR;
        this.descR = descR;
        this.horairesR = horairesR;
        this.photoPrincipal = photoPrincipal;


    }

    public Resto(String nomR, int numAdrR, String voieAdrR, int cpR, String villeR, float latitudeDegR, float longitudeDegR, String descR, String horairesR, String photoPrincipal) {
        this.nomR = nomR;
        this.numAdrR = numAdrR;
        this.voieAdrR = voieAdrR;
        this.cpR = cpR;
        this.villeR = villeR;
        this.latitudeDegR = latitudeDegR;
        this.longitudeDegR = longitudeDegR;
        this.descR = descR;
        this.horairesR = horairesR;
        this.photoPrincipal = photoPrincipal;
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public String getNomR() {
        return nomR;
    }

    public void setNomR(String nomR) {
        this.nomR = nomR;
    }

    public int getNumAdrR() {
        return numAdrR;
    }

    public void setNumAdrR(int numAdrR) {
        this.numAdrR = numAdrR;
    }

    public String getVoieAdrR() {
        return voieAdrR;
    }

    public void setVoieAdrR(String voieAdrR) {
        this.voieAdrR = voieAdrR;
    }

    public int getCpR() {
        return cpR;
    }

    public void setCpR(int cpR) {
        this.cpR = cpR;
    }

    public String getVilleR() {
        return villeR;
    }

    public void setVilleR(String villeR) {
        this.villeR = villeR;
    }

    public float getLatitudeDegR() {
        return latitudeDegR;
    }

    public void setLatitudeDegR(float latitudeDegR) {
        this.latitudeDegR = latitudeDegR;
    }

    public float getLongitudeDegR() {
        return longitudeDegR;
    }

    public void setLongitudeDegR(float longitudeDegR) {
        this.longitudeDegR = longitudeDegR;
    }

    public String getDescR() {
        return descR;
    }

    public void setDescR(String descR) {
        this.descR = descR;
    }

    public String getHorairesR() {
        return horairesR;
    }

    public void setHorairesR(String horairesR) {
        this.horairesR = horairesR;
    }

    public String getPhotoPrincipal() {
        return photoPrincipal;
    }

    public void setPhotoPrincipal(String photoPrincipal) {
        this.photoPrincipal = photoPrincipal;
    }

    @Override
    public String toString() {
        return "Resto{" +
                "idR=" + idR +
                ", nomR='" + nomR + '\'' +
                ", numAdrR='" + numAdrR + '\'' +
                ", voieAdrR='" + voieAdrR + '\'' +
                ", cpR=" + cpR +
                ", villeR='" + villeR + '\'' +
                ", latitudeDegR=" + latitudeDegR +
                ", longitudeDegR=" + longitudeDegR +
                ", descR='" + descR + '\'' +
                ", horairesR='" + horairesR + '\'' +
                '}';
    }
}
