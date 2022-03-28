package btssio.appliresto.modele;

/**
 * Created by mick.souloumiac1 on 17/03/2022.
 */

public class Aimer {

    private int idR;
    private String mailU;
    private boolean aime;

    public Aimer(int idR, String mailU, boolean aime) {
        this.idR = idR;
        this.mailU = mailU;
        this.aime = aime;
    }

    public int getIdR() {
        return idR;
    }


    public void setIdR(int idR) {
        this.idR = idR;
    }


    public String getMailU() {
        return mailU;
    }


    public void setMailU(String mailU) {
        this.mailU = mailU;
    }


    public boolean isAime() {
        return aime;
    }


    public void setAime(boolean aime) {
        this.aime = aime;
    }


    @Override
    public String toString() {
        return "Aimer{" +
                "idR=" + idR +
                ", mailU='" + mailU + '\'' +
                ", aime=" + aime +
                '}';
    }

}
